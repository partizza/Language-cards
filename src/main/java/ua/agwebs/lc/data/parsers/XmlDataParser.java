package ua.agwebs.lc.data.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class XmlDataParser implements DataParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlDataParser.class);

    private File srcFile;

    public XmlDataParser(File srcFile) {
        if(srcFile == null) throw new IllegalArgumentException("srcFile can't be null.");
        this.srcFile = srcFile;
    }

    @Override
    public Map<String, Set<String>> parse() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlCards.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XmlCards xmlCards = (XmlCards) unmarshaller.unmarshal(srcFile);
            return xmlCards.getCards();
        } catch (JAXBException e) {
            LOGGER.error("Can't parse a file", e);
            throw new RuntimeException("Can't parse data source.", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XmlDataParser that = (XmlDataParser) o;

        return srcFile != null ? srcFile.equals(that.srcFile) : that.srcFile == null;
    }

    @Override
    public int hashCode() {
        return srcFile != null ? srcFile.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "XmlDataParser{" +
                "srcFile=" + srcFile +
                '}';
    }
}
