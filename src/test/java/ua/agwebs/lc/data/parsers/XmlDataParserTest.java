package ua.agwebs.lc.data.parsers;

import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Set;

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
        Map<String, Set<String>> result = xmlDataParser.parse();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testOnExampleData(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        XmlDataParser xmlDataParser = new XmlDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_NAME).getFile()));
        Map<String, Set<String>> result = xmlDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.",result.size() == 4);
    }

    @Test
    public void testOnExampleDataWithDuplication(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        XmlDataParser xmlDataParser = new XmlDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_WITH_DUPL_NAME).getFile()));
        Map<String, Set<String>> result = xmlDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.",result.size() == 2);
        for(Map.Entry<String, Set<String>> entry : result.entrySet()){
            assertTrue("Unexpected number of values ",entry.getValue().size() == 2);
        }
    }
}