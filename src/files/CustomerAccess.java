package files;

import People.Person;
import People.PersonGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerAccess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = readListPeople();

        while (true) {
            System.out.println("""
                    Нажмите кнопку, чтобы:
                    1 - Добавить нового пользователя
                    2 - Показать всех пользователей
                    5 - выход""");
            System.out.print("Введите команду ");
            int knopka = scanner.nextInt();
            if (knopka == 1) {
                System.out.print("Введите имя пользователя: ");
                String name = scanner.next();
                System.out.print("Введите город пользователя: ");
                String city = scanner.next();

                Person person = new Person(name, 15, city);
                people.add(person);
            } else if (knopka == 2) {
                System.out.println("Список пользователей: ");
                printPerson(people);
            } else if (knopka == 5) {
                System.out.println("До свидания!");
                break;
            }
        }

        writeFile(people);
    }

    static void writeFile(List<Person> persons) {
        try (FileWriter writer = new FileWriter("src/files/people.txt")) {
            for (Person p : persons) {
                writer.write(p.name + " " + p.age + " " + p.city + "\n");
            }
        } catch (IOException e) {
            System.out.println("Произошла файловая ошибка " + e.getMessage());
        }
    }

    static List<Person> readListPeople() {
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

            return people;

        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void printPerson(List<Person> people) {
        for (Person p : people) {
            System.out.println("Имя:" + p.name + " Город:" + p.city);
        }
    }
}
