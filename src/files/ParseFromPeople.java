package files;

import lesson_class.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseFromPeople {
    public static void main(String[] args) {
        readListPeople();
    }

    static void readListPeople() {
        try {
            File file = new File("src/files/people.txt");
            Scanner scanner = new Scanner(file);
            List<Person> people = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");

                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                String city = parts[2];

                people.add(new Person(name, age, city));
            }

            System.out.println(people);

        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }
}
