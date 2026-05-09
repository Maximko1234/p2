package bankomat;

import java.io.File;
import java.util.*;

public class Storage {

    public Map<String, Integer> balance = new HashMap<>();
    public Map<String, String> logins = new HashMap<>();

    public Storage() {
        try {
            File file = new File("src/bankomat/storage/balance.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                String login = parts[0];
                int summa = Integer.parseInt(parts[1]);

                balance.put(login, summa);
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }

        try {
            File file = new File("src/bankomat/storage/logins.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                String login = parts[0];
                String password = parts[1];

                logins.put(login, password);
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }
}