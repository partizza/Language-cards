package ua.agwebs.lc.data.parsers;

import ua.agwebs.lc.decks.CardDeck;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface DataParser {

    CardDeck<String,String> parse();
}
