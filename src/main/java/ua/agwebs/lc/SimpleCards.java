package ua.agwebs.lc;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class SimpleCards extends CardBox {

    private File src;
    private Map<String, String> carts;

    public SimpleCards(File src) {
        this.src = src;
    }

    @Override
    protected void initData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(src))) {
            carts = new HashMap<>();
            String str = null;
            while ((str = reader.readLine()) != null) {
                String[] split = str.split("=");
                carts.put(split[0].trim(), split[1].trim());
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
                Iterator<Map.Entry<String, String>> iterator = carts.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    System.out.println(next.getKey());
                    String input = scanner.nextLine();
                    if (input.equals(next.getValue())) iterator.remove();
                }
            }
        }
    }
}
