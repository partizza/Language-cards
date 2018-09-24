import ua.agwebs.lc.CardBox;
import ua.agwebs.lc.FileCards;
import ua.agwebs.lc.data.parsers.XmlCards;
import ua.agwebs.lc.data.parsers.XmlDataParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        if (args.length < 1) throw new IllegalArgumentException("Source file must be specified.");

//        CardBox cardBox = new FileCards(new CsvDataParser(new File(args[0]), "=", ";"));
        CardBox cardBox = new FileCards(new XmlDataParser(new File(args[0])));
        cardBox.execute();
    }
}
