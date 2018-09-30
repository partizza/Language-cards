import ua.agwebs.lc.CardsExecutor;
import ua.agwebs.lc.FileCardsExecutor;
import ua.agwebs.lc.data.parsers.CsvDataParser;
import ua.agwebs.lc.data.parsers.XmlDataParser;

import java.io.File;

public class App {

    public static void main(String[] args) {

        if (args.length < 1) throw new IllegalArgumentException("Source file must be specified.");

        CardsExecutor cardBox = new FileCardsExecutor(new CsvDataParser(new File(args[0]), "=", ";"));
//        CardsExecutor cardBox = new FileCardsExecutor(new XmlDataParser(new File(args[0])));
        cardBox.execute();
    }
}
