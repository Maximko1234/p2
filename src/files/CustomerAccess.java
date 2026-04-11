package files;

import People.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerAccess {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>(); //TODO считывать из файла

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

        //TODO добавить пользователей в файл
    }

    private static void printPerson(List<Person> people) {
        for (Person p : people) {
            System.out.println("Имя:" + p.name + " Город:" + p.city);
        }
    }
}
