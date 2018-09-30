package ua.agwebs.lc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.agwebs.lc.data.parsers.DataParser;
import ua.agwebs.lc.decks.CardDeck;

import java.util.*;

public class FileCardsExecutor extends CardsExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileCardsExecutor.class);

    private static final String VAL_SEPARATOR = ";";

    private CardDeck<String, String> cards;
    private DataParser dataParser;

    public FileCardsExecutor(DataParser parser) {
        this.dataParser = parser;
    }

    @Override
    protected void initData() {
        cards = dataParser.parse();
    }

    @Override
    protected void drill() {
        try (Scanner scanner = new Scanner(System.in)) {
            Set<String> kSet = new HashSet<>(cards.getKeys());
            while (!kSet.isEmpty()) {
                Iterator<String> iterator = kSet.iterator();
                while (iterator.hasNext()) {
                    System.out.println();

                    String next = iterator.next();
                    Set<String> values = cards.getValues(next);
                    int size = 0;
                    System.out.println(next + ((size = values.size()) > 1 ? " (" + size + ") " : ""));
                    String input = scanner.nextLine();

                    String[] inputs = input.split(VAL_SEPARATOR);
                    if (inputs.length < size) {
                        System.out.println("NOT ALL VALUES !!!");
                        continue;
                    }
                    if (inputs.length > size) {
                        System.out.println("TOO MUCH VALUES !!!");
                        continue;
                    }

                    boolean isCorrect = true;
                    for (String ans : input.split(VAL_SEPARATOR)) {
                        if (values.contains(ans.trim())) continue;
                        else {
                            isCorrect = false;
                            System.out.println("NOT CORRECT !!!");
                            break;
                        }
                    }

                    if (isCorrect) {
                        iterator.remove();
                        System.out.println("CORRECT !!!");
                    }
                }
            }
        }
    }
}
