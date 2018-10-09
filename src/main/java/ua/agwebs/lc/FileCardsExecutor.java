package ua.agwebs.lc;

import ua.agwebs.lc.data.parsers.DataParser;
import ua.agwebs.lc.decks.CardDeck;
import ua.agwebs.lc.simulation.Simulator;

class FileCardsExecutor extends CardsExecutor {

    private CardDeck<String, String> cards;
    private DataParser dataParser;
    private Simulator simulator;

    public FileCardsExecutor(DataParser parser, Simulator simulator) {
        this.dataParser = parser;
        this.simulator = simulator;
    }

    @Override
    protected void initData() {
        cards = dataParser.parse();
    }

    @Override
    protected void drill() {
        simulator.drill(cards);
    }
}
