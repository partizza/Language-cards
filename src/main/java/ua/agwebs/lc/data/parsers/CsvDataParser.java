package ua.agwebs.lc.data.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.agwebs.lc.decks.CardDeck;
import ua.agwebs.lc.decks.MultiValueCardDeck;

import java.io.*;

public class CsvDataParser implements DataParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvDataParser.class);

    private String langSeparator;
    private String valSeparator;
    private File srcFile;

    public CsvDataParser(File srcFile, String langSeparator, String valSeparator) {
        if (srcFile == null || langSeparator == null || valSeparator == null) throw new IllegalArgumentException();

        this.langSeparator = langSeparator;
        this.valSeparator = valSeparator;
        this.srcFile = srcFile;

        LOGGER.trace("{} created with attributes: {}", this.getClass().getName(), this.toString());
    }

    @Override
    public CardDeck<String, String> parse() {
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))) {
            MultiValueCardDeck carts = new MultiValueCardDeck();

            String str = null;
            while ((str = reader.readLine()) != null) {
                LOGGER.trace("Input line: {}", str);

                str = str.trim();
                if (str.isEmpty()) continue;

                String[] split = str.split(langSeparator);
                if (split.length != 2) throw new RuntimeException("Unexpected file content.");

                String[] keys = split[0].split(valSeparator);
                String[] values = split[1].split(valSeparator);

                for (String k : keys) {
                    for (String v : values) carts.put(k.trim(), v.trim());
                }
            }

            return carts;
        } catch (IOException e) {
            LOGGER.error("Can't parse a file", e);
            throw new RuntimeException("Can't parse data source.", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CsvDataParser that = (CsvDataParser) o;

        if (langSeparator != null ? !langSeparator.equals(that.langSeparator) : that.langSeparator != null)
            return false;
        if (valSeparator != null ? !valSeparator.equals(that.valSeparator) : that.valSeparator != null) return false;
        return srcFile != null ? srcFile.equals(that.srcFile) : that.srcFile == null;
    }

    @Override
    public int hashCode() {
        int result = langSeparator != null ? langSeparator.hashCode() : 0;
        result = 31 * result + (valSeparator != null ? valSeparator.hashCode() : 0);
        result = 31 * result + (srcFile != null ? srcFile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CsvDataParser{" +
                "langSeparator='" + langSeparator + '\'' +
                ", valSeparator='" + valSeparator + '\'' +
                ", srcFile=" + srcFile +
                '}';
    }
}
