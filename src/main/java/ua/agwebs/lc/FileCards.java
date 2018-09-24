package ua.agwebs.lc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.agwebs.lc.data.parsers.DataParser;

import java.io.IOException;
import java.util.*;

public class FileCards extends CardBox {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileCards.class);

    private static final String VAL_SEPARATOR = ";";

    private Map<String, Set<String>> carts;
    private DataParser dataParser;

    public FileCards(DataParser parser) {
        this.dataParser = parser;
    }

    @Override
    protected void initData() {
            carts = dataParser.parse();
    }

    @Override
    protected void drill() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!carts.isEmpty()) {
                Iterator<Map.Entry<String, Set<String>>> iterator = carts.entrySet().iterator();
                while (iterator.hasNext()) {
                    System.out.println();

                    Map.Entry<String, Set<String>> next = iterator.next();
                    int size = 0;
                    System.out.println(next.getKey() + ((size = next.getValue().size()) > 1 ? " (" + size + ") " : ""));
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
                        if (next.getValue().contains(ans.trim())) continue;
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
