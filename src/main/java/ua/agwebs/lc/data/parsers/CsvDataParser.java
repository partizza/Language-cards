package ua.agwebs.lc.data.parsers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CsvDataParser implements DataParser {

    private String langSeparator;
    private String valSeparator;
    private File srcFile;

    public CsvDataParser(File srcFile, String langSeparator, String valSeparator) {
        this.langSeparator = langSeparator;
        this.valSeparator = valSeparator;
        this.srcFile = srcFile;
    }

    @Override
    public Map<String, Set<String>> parse() throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFile))) {
            Map<String, Set<String>> carts = new HashMap<>();

            String str = null;
            while ((str = reader.readLine()) != null) {
                String[] split = str.split(langSeparator);
                String[] keys = split[0].split(valSeparator);
                String[] values = split[1].split(valSeparator);

                for (String k : keys) {
                    k = k.trim();
                    Set<String> set = carts.getOrDefault(k.trim(), new TreeSet<>());
                    for (String v : values) set.add(v.trim());
                    carts.put(k, set);
                }
            }

            return carts;
        }
    }
}
