package ua.agwebs.lc.data.parsers;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

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
    public void testEmptyFile() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        CsvDataParser csvDataParser = new CsvDataParser(new File(classLoader.getResource(SRC_EMPTY_FILE_NAME).getFile()), "", "");
        Map<String, Set<String>> result = csvDataParser.parse();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testOnExampleData() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        CsvDataParser csvDataParser = new CsvDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_NAME).getFile()), "=", ";");
        Map<String, Set<String>> result = csvDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.",result.size() == 4);
    }

    @Test
    public void testOnExampleDataWithDuplication() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        CsvDataParser csvDataParser = new CsvDataParser(new File(classLoader.getResource(SRC_TEST_DATA_FILE_WITH_DUPL_NAME).getFile()), "=", ";");
        Map<String, Set<String>> result = csvDataParser.parse();

        assertNotNull(result);
        assertTrue("Unexpected number of key-value pair.",result.size() == 2);
        for(Map.Entry<String, Set<String>> entry : result.entrySet()){
            assertTrue("Unexpected number of values ",entry.getValue().size() == 2);
        }
    }
}