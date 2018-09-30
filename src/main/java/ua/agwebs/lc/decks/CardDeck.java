package ua.agwebs.lc.decks;

import java.util.Map;
import java.util.Set;

public abstract class CardDeck<K, V> {

    protected Map<K, Set<V>> deck;

    /**
     * Associates the specified value with the specified key.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public abstract void put(K key, V value);

    /**
     * Remove the mapping for key if it is present (optional)
     *
     * @param key key with which values are associated
     */
    public void remove(K key) {
        deck.remove(key);
    }

    /**
     * Remove the key-value pair if it is present (optional)
     *
     * @param key   key with which value are associated
     * @param value value associated with key
     */
    public void remove(K key, V value) {
        Set<V> vSet = deck.get(key);
        if (vSet != null) vSet.remove(value);
    }

    /**
     * Retrieves all included keys
     *
     * @return set of included keys
     */
    public Set<K> getKeys() {
        return deck.keySet();
    }

    /**
     * Retrieves values associated with a specified key
     *
     * @param key key with which values are associated
     * @return set of values associated with key
     */
    public Set<V> getValues(K key) {
        return deck.get(key);
    }

    @Override
    public String toString() {
        return "CardDeck{" +
                "deck=" + deck +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardDeck<?, ?> cardDeck = (CardDeck<?, ?>) o;

        return deck != null ? deck.equals(cardDeck.deck) : cardDeck.deck == null;
    }

    @Override
    public int hashCode() {
        return deck != null ? deck.hashCode() : 0;
    }
}
