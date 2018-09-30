package ua.agwebs.lc.decks;

import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MultiValueCardDeckTest {

    @Test(expected = IllegalArgumentException.class)
    public void testPut_NullKey() {
        MultiValueCardDeck multiValueCardDeck = new MultiValueCardDeck();
        multiValueCardDeck.put(null, "some value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut_NullValue() {
        MultiValueCardDeck multiValueCardDeck = new MultiValueCardDeck();
        multiValueCardDeck.put("some key", null);
    }

    @Test
    public void testPut() {
        MultiValueCardDeck multiValueCardDeck = new MultiValueCardDeck();
        int numb = ThreadLocalRandom.current().nextInt(2, 16);
        for (int i = 0; i < numb; i++) multiValueCardDeck.put("k" + String.valueOf(i), "v" + String.valueOf(i));

        for (int i = 0; i < numb; i++) {
            String si = String.valueOf(i);
            assertNotNull(multiValueCardDeck.getValues("k" + si));
            assertTrue(multiValueCardDeck.getValues("k" + si).contains("v" + si));
        }
    }

    @Test
    public void testPut_DuplicateValue() {
        MultiValueCardDeck multiValueCardDeck = new MultiValueCardDeck();

        // test data
        final String val = "some value";
        final String key = "key";
        int numb = ThreadLocalRandom.current().nextInt(2, 10);
        for (int i = 0; i < numb; i++) multiValueCardDeck.put(key, val);

        assertNotNull(multiValueCardDeck.getValues(key));
        assertTrue(multiValueCardDeck.getValues(key).size() == 1);
        assertTrue(multiValueCardDeck.getValues(key).contains(val));
    }
}