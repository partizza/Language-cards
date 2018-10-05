package ua.agwebs.lc;

import ua.agwebs.lc.cli.CliService;
import ua.agwebs.lc.data.parsers.CsvDataParser;
import ua.agwebs.lc.data.parsers.DataParser;
import ua.agwebs.lc.data.parsers.XmlDataParser;

import java.io.File;

public class CardsExecutorFactory {

    public static final String CSV_LANG_SEPARATOR_DEFAULT_VAL = "=";
    public static final String CSV_VAL_SEPARATOR_DEFAULT_VAL = "=";

    public static CardsExecutor create(CliService cli) {
        if (cli == null || !cli.isValid()) throw new IllegalArgumentException("Not valid command line.");

        DataParser dataParser = createDataParser(cli);
        return new FileCardsExecutor(dataParser);

    }

    protected static DataParser createDataParser(CliService cli) {
        String fFormat = cli.getSwitcherValue(SettingsOption.FORMAT.toString().toLowerCase(), "csv");
        DataParser dataParser = null;
        switch (fFormat) {
            case "csv":
                dataParser = new CsvDataParser(
                        new File(cli.getTarget(0)),
                        cli.getSwitcherValue(SettingsOption.LSPR.toString().toLowerCase(), CSV_LANG_SEPARATOR_DEFAULT_VAL),
                        cli.getSwitcherValue(SettingsOption.VSPR.toString().toLowerCase(), CSV_VAL_SEPARATOR_DEFAULT_VAL));
                break;
            case "xml":
                dataParser = new XmlDataParser(new File(cli.getTarget(0)));
                break;
            default:
                throw new IllegalArgumentException("Unsupported file format.");
        }

        return dataParser;
    }

}
