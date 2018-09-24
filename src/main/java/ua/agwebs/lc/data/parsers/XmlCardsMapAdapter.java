package ua.agwebs.lc.data.parsers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;

public class XmlCardsMapAdapter extends XmlAdapter<XmlCardsMapAdapter.AdaptedMap, Map<String, Set<String>>> {

    public static class AdaptedMap {

        @XmlElement(name = "card")
        public List<Entry> entries = new ArrayList<Entry>();

    }

    public static class Entry {

        @XmlElement(name = "expression")
        public String key;

        @XmlElementWrapper(name = "translation")
        @XmlElement(name = "value")
        public Set<String> values;

        public Entry() {
        }

        public Entry(String key, Set<String> values) {
            this.key = key;
            this.values = values;
        }
    }

    @Override
    public Map<String, Set<String>> unmarshal(AdaptedMap v) throws Exception {
        HashMap<String, Set<String>> resultMap = new HashMap<>();
        for (Entry e : v.entries) {
            Set<String> set = resultMap.getOrDefault(e.key, new HashSet<>());
            set.addAll(e.values);
            resultMap.put(e.key, set);
        }
        return resultMap;
    }

    @Override
    public AdaptedMap marshal(Map<String, Set<String>> v) throws Exception {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<String, Set<String>> e : v.entrySet()) {
            adaptedMap.entries.add(new Entry(e.getKey(), e.getValue()));
        }
        return adaptedMap;
    }
}
