package ua.agwebs.lc.data.parsers;

import java.util.Map;
import java.util.Set;

public interface DataParser {

    Map<String, Set<String>> parse() throws Exception;
}
