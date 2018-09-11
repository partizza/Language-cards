package ua.agwebs.lc;

import java.io.*;
import java.util.*;

public class SimpleCards extends CardBox {

    static final private String LANG_SEPARATOR = "=";
    static final private String VAL_SEPARATOR = ";";

    private File src;
    private Map<String, Set<String>> carts;

    public SimpleCards(File src) {
        this.src = src;
    }

    @Override
    protected void initData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(src))) {
            carts = new HashMap<>();
            String str = null;
            while ((str = reader.readLine()) != null) {
                String[] split = str.split(LANG_SEPARATOR);
                String[] keys = split[0].split(VAL_SEPARATOR);
                String[] values = split[1].split(VAL_SEPARATOR);
                for (String k : keys) {
                    k = k.trim();
                    Set<String> set = carts.getOrDefault(k.trim(), new TreeSet<>());
                    for (String v : values) set.add(v.trim());
                    carts.put(k, set);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
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
