package ua.agwebs.lc.simulation;

import ua.agwebs.lc.decks.CardDeck;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SimpleConsoleSimulator implements Simulator {

    private final String delimiter;

    public SimpleConsoleSimulator(String delimiter) {
        if (delimiter == null || delimiter.isEmpty()) throw new IllegalArgumentException();

        this.delimiter = delimiter;
    }

    @Override
    public void drill(CardDeck<String, String> cards) {
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

                    String[] inputs = input.split(delimiter);
                    if (inputs.length < size) {
                        System.out.println("NOT ALL VALUES !!!");
                        continue;
                    }
                    if (inputs.length > size) {
                        System.out.println("TOO MUCH VALUES !!!");
                        continue;
                    }

                    boolean isCorrect = true;
                    for (String ans : input.split(delimiter)) {
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
