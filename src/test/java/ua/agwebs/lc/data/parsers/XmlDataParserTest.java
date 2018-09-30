package ua.agwebs.lc.data.parsers;

import org.junit.Test;
import ua.agwebs.lc.decks.CardDeck;

import java.io.File;

import static org.junit.Assert.*;

public class XmlDataParserTest {

    private static final String SRC_EMPTY_FILE_NAME = "src_empty_file.txt";
    private static final String SRC_FILE_WITHOUT_DATA_NAME = "src_without_data.xml";
    private static final String SRC_TEST_DATA_FILE_NAME = "src_test_data.xml";
    private static final String SRC_TEST_DATA_FILE_WITH_DUPL_NAME = "src_test_data_duplication.xml";

    @Test(expected = IllegalArgumentException.class)
    public void testNullParams_srcFile() {
        XmlDataParser xmlDataParser = new XmlDataParser(null);
        xmlDataParser.parse();
    }

    @Test(expected = RuntimeException.class)
    public void testNonExistingFile() {
        XmlDataParser xmlDataParser = new XmlDataParser(new File("some_non_existing_file.435123534"));
        xmlDataParser.parse();
    }

    @Test(expected = RuntimeException.class)
    public void testEmptyFile() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        XmlDataParser xmlDataParser = new XmlDataParser(new File(classLoader.getResource(SRC_EMPTY_FILE_NAME).getFile()));
        xmlDataParser.parse();
    }

    @Test
    public void testEmptyXmlFile() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        XmlDataParser xmlDataParser = new XmlDataParser(new File(classLoader.getResource(SRC_FILE_WITHOUT_DATA_NAME).getFile()));
        CardDeck<String, String> result = xmlDataParser.parse();

        assertNotNull(result);
        assertTrue(result.getKeys().isEmpty());
    }

    @Test
    public void testOnExampleData(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        XmlDataParser xmlDataParser = new XmlDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_NAME).getFile()));
        CardDeck<String, String> result = xmlDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.",result.getKeys().size() == 4);
    }

    @Test
    public void testOnExampleDataWithDuplication(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        XmlDataParser xmlDataParser = new XmlDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_WITH_DUPL_NAME).getFile()));
        CardDeck<String, String> result = xmlDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.",result.getKeys().size() == 2);
        for(String k : result.getKeys()){
            assertTrue("Unexpected number of values ",result.getValues(k).size() == 2);
        }
    }
}