package ua.agwebs.lc;

import org.junit.Test;
import ua.agwebs.lc.cli.CliHelper;
import ua.agwebs.lc.cli.CliService;
import ua.agwebs.lc.data.parsers.CsvDataParser;
import ua.agwebs.lc.data.parsers.DataParser;
import ua.agwebs.lc.data.parsers.XmlDataParser;

import static org.junit.Assert.*;

public class CardsExecutorFactoryTest {

    private static class CardsExecutorFactoryExt extends CardsExecutorFactory {
        public static DataParser createDataParser(CliService cli) {
            return CardsExecutorFactory.createDataParser(cli);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullArg() {
        CardsExecutorFactory.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmptyTargets() {
        String args[] = new String[]{"--format=csv", "-czx", "--opt=beta"};
        CardsExecutorFactory.create(new CliHelper(args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateMoreThanOneTarget() {
        String args[] = new String[]{"--format=csv", "-czx", "--opt=beta", "some_data.txt", "other_data.txt"};
        CardsExecutorFactory.create(new CliHelper(args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateMissedFileFormat() {
        String args[] = new String[]{"--format=", "-czx", "--opt=beta", "some_data.txt"};
        CardsExecutorFactory.create(new CliHelper(args));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmptyFileFormat() {
        String args[] = new String[]{"--format=   ", "-czx", "--opt=beta", "some_data.txt"};
        CardsExecutorFactory.create(new CliHelper(args));
    }

    @Test
    public void testCreateCsvDataParserDefaultValues() {
        final String fName = "some_data.txt";
        String args[] = new String[]{"--format=csv", fName};
        DataParser dataParser = CardsExecutorFactory.createDataParser(new CliHelper(args));

        assertNotNull(dataParser);
        assertTrue("Unexpected instance.", dataParser instanceof CsvDataParser);
        assertTrue(CardsExecutorFactory.CSV_LANG_SEPARATOR_DEFAULT_VAL.equals(CsvDataParser.CsvDataParserTestClass.getLangSeparator((CsvDataParser) dataParser)));
        assertTrue(CardsExecutorFactory.CSV_VAL_SEPARATOR_DEFAULT_VAL.equals(CsvDataParser.CsvDataParserTestClass.getValSeparator((CsvDataParser) dataParser)));
        assertTrue(fName.equals(CsvDataParser.CsvDataParserTestClass.getFileName((CsvDataParser) dataParser)));
    }

    @Test
    public void testCreateCsvDataParser() {
        final String fName = "some_data.txt";
        final String lSep = "->";
        final String vSep = "|";

        String args[] = new String[]{"--format=csv", "--lspr=" + lSep, "--vspr=" + vSep, fName};
        DataParser dataParser = CardsExecutorFactory.createDataParser(new CliHelper(args));

        assertNotNull(dataParser);
        assertTrue("Unexpected instance.", dataParser instanceof CsvDataParser);
        assertTrue(lSep.equals(CsvDataParser.CsvDataParserTestClass.getLangSeparator((CsvDataParser) dataParser)));
        assertTrue(vSep.equals(CsvDataParser.CsvDataParserTestClass.getValSeparator((CsvDataParser) dataParser)));
        assertTrue(fName.equals(CsvDataParser.CsvDataParserTestClass.getFileName((CsvDataParser) dataParser)));
    }

    @Test
    public void testCreateXmlDataParser() {
        final String fName = "some_data.xml";
        String args[] = new String[]{"--format=xml", fName};
        DataParser dataParser = CardsExecutorFactory.createDataParser(new CliHelper(args));

        assertNotNull(dataParser);
        assertTrue("Unexpected instance.", dataParser instanceof XmlDataParser);
        assertTrue(fName.equals(XmlDataParser.XmlDataParserTestClass.getFileName((XmlDataParser) dataParser)));
    }

    @Test
    public void testCreateFileCardsExecutor() {
        String args[] = new String[]{"--format=xml", "some_data.txt"};
        CardsExecutor cardsExecutor = CardsExecutorFactory.create(new CliHelper(args));

        assertNotNull(cardsExecutor);
        assertTrue("Unexpected instance.", cardsExecutor instanceof FileCardsExecutor);
    }
}