package ua.agwebs.lc.simulation;

import ua.agwebs.lc.decks.CardDeck;

public interface Simulator {

    void drill(CardDeck<String, String> cards);
}
