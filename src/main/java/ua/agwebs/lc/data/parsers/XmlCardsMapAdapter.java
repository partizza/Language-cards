package ua.agwebs.lc.data.parsers;

import ua.agwebs.lc.decks.CardDeck;
import ua.agwebs.lc.decks.MultiValueCardDeck;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;

public class XmlCardsMapAdapter extends XmlAdapter<XmlCardsMapAdapter.AdaptedMap, CardDeck<String, String>> {

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
    public CardDeck<String, String> unmarshal(AdaptedMap v) throws Exception {
        CardDeck<String, String> result = new MultiValueCardDeck();
        for (Entry entry : v.entries) {
            entry.values.stream().forEach(val -> result.put(entry.key.trim(), val.trim()));
        }
        return result;
    }

    @Override
    public AdaptedMap marshal(CardDeck<String, String> v) throws Exception {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (String k : v.getKeys()) {
            adaptedMap.entries.add(new Entry(k, v.getValues(k)));
        }
        return adaptedMap;
    }
}
