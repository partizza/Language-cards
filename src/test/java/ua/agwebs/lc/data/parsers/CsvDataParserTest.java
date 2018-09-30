package ua.agwebs.lc.data.parsers;

import org.junit.Test;
import ua.agwebs.lc.decks.CardDeck;

import java.io.File;

import static org.junit.Assert.*;

public class CsvDataParserTest {

    private static final String SRC_EMPTY_FILE_NAME = "src_empty_file.txt";
    private static final String SRC_TEST_DATA_FILE_NAME = "src_test_data.txt";
    private static final String SRC_TEST_DATA_FILE_WITH_DUPL_NAME = "src_test_data_duplication.txt";

    @Test(expected = IllegalArgumentException.class)
    public void testNullParams_srcFile() {
        new CsvDataParser(null, "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullParams_langSeparator() {
        new CsvDataParser(new File("some_file"), null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullParams_valSeparator() {
        new CsvDataParser(new File("some_file"), "", null);
    }

    @Test
    public void testEmptyFile() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        CsvDataParser csvDataParser = new CsvDataParser(new File(classLoader.getResource(SRC_EMPTY_FILE_NAME).getFile()), "", "");
        CardDeck<String, String> deck = csvDataParser.parse();

        assertNotNull(deck);
        assertTrue(deck.getKeys().isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testNonExitingFile() {
        CsvDataParser csvDataParser = new CsvDataParser(new File("nameOfNonExitingFile.12342450"), "", "");
        CardDeck<String, String> result = csvDataParser.parse();
    }

    @Test
    public void testOnExampleData() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        CsvDataParser csvDataParser = new CsvDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_NAME).getFile()), "=", ";");
        CardDeck<String, String> result = csvDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.", result.getKeys().size() == 4);
    }

    @Test
    public void testOnExampleDataWithDuplication() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        CsvDataParser csvDataParser = new CsvDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_WITH_DUPL_NAME).getFile()), "=", ";");
        CardDeck<String, String> result = csvDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.", result.getKeys().size() == 2);
        for (String k : result.getKeys()) {
            assertTrue("Unexpected number of values ", result.getValues(k).size() == 2);
        }
    }
}