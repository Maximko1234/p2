package bankomat;

import People.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public void save() {
        System.out.println("Сохранение данных в файл...");
        try (FileWriter writer = new FileWriter("src/bankomat/storage/balance.txt")) {
            for (var b : balance.entrySet()) {
                writer.write(b.getKey() + " " + b.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Произошла файловая ошибка " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("src/bankomat/storage/logins.txt")) {
            for (var b : logins.entrySet()) {
                writer.write(b.getKey() + " " + b.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Произошла файловая ошибка " + e.getMessage());
        }
    }
}