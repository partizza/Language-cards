package ua.agwebs.lc.decks;

import java.util.*;

public class MultiValueCardDeck extends CardDeck<String, String> {


    public MultiValueCardDeck() {
        this.deck = new TreeMap<>();
    }

    @Override
    public void put(String key, String value) {
        if (key == null || value == null) throw new IllegalArgumentException("Params can't be null.");

        Set<String> valSet = deck.getOrDefault(key, new TreeSet<>());
        valSet.add(value);
        deck.put(key, valSet);
    }

}
