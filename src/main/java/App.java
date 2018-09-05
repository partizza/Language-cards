import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        if (args.length < 1) throw new IllegalArgumentException("Source file must be specified.");
        Map<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String str = null;
            while ((str = reader.readLine()) != null) {
                String[] split = str.split("=");
                map.put(split[0].trim(), split[1].trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        try (Scanner scanner = new Scanner(System.in)) {
            while (!map.isEmpty()) {
                Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
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
