import ua.agwebs.lc.CardBox;
import ua.agwebs.lc.SimpleCards;

import java.io.*;

public class App {

    public static void main(String[] args) {

        if (args.length < 1) throw new IllegalArgumentException("Source file must be specified.");

        CardBox cardBox = new SimpleCards(new File(args[0]));
        cardBox.execute();
    }
}
