package socialNetwork;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int knopka = scanner.nextInt();
            if (knopka == 1) {
                registration(scanner, storage);
            } else if (knopka == 2) {
               printUsers(storage);
            } else if (knopka == 3) {
                //введите логин кого хотите добавить

            } else if (knopka == 4) {
                //найти человека по введенному имени

                /*
                 * Шаг 1. попросить ввести имя
                 * Шаг 2. Перебрать список людей и найти совпадение с именем
                 * Шаг 3 вывести информацию о человеке
                 */

            } else if (knopka == 5) {
                System.out.println("До свидания!");
                break;
            }
        }

        storage.save();
    }

    static void printUsers(UserStorage storage) {
        List<Person> personlist = storage.getPersons();
        for (Person person : personlist) {
            System.out.println(person.getLogin() +
                    ": " + person.getFirstName() + " " +
                    person.getLastName() + " город " +
                    person.getCity());
        }
    }

    static void printMenu(){
        System.out.println("""
                    Нажмите кнопку, чтобы:
                    1 - Добавить нового пользователя
                    2 - Вывод всех пользователей на экран
                    3 - Добавить друга
                    4 - 
                    5 - выход
                    6 -""");
        System.out.print("Введите команду ");
    }

    static void registration(Scanner scanner, UserStorage storage) {
        System.out.print("Введите логин: ");
        String login = scanner.next();
        System.out.print("Введите имя: ");
        String firstName = scanner.next();
        System.out.print("Введите фамилию: ");
        String lastName = scanner.next();
        System.out.print("Введите город: ");
        String city = scanner.next();
        System.out.print("Введите дату рождения введите год ");
        int year = scanner.nextInt();
        System.out.print("Введите дату рождения введите месяц ");
        int month = scanner.nextInt();
        System.out.print("Введите дату рождения введите день ");
        int day = scanner.nextInt();

        Person newPerson = new Person(login, firstName, lastName, city, LocalDate.of(year, month , day));
        storage.savePerson(newPerson);
        System.out.println("Пользователь добавлен.айди пользваотеля: " + newPerson.getId());
    }

}