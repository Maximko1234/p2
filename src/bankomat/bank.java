package bankomat;

import java.util.Scanner;

public class bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String login = login(scanner, storage);
        boolean successLogin;
        if (login.isEmpty()) {
            successLogin = false;
        } else {
            successLogin = true;
        }
        while (successLogin) {
            System.out.println("""
                нажмите кнопку чтобы:);
                1 - пополнить баланс
                2 - снять деньги
                3 - перевести на другой счёт
                4 - узнать баланс
                5 - выйти""");
            System.out.println("введите номер команды");
            int knopka = scanner.nextInt();
            if (knopka == 1) {
                popolnenie(scanner, storage, login);
            } else if (knopka == 2) {
                snyatie(scanner, storage, login);
            } else if (knopka == 3) {
                perevod(scanner, storage, login);
            } else if (knopka == 4) {
                int balance = storage.balance.get(login);
                System.out.println("ваш баланс" + balance);
            } else if (knopka == 5) {
                System.out.println("до свидания");
                break;
            }
        }
    }

    public static String login(Scanner scanner, Storage storage) {
        System.out.println("добро пожаловать!введи логин и пароль");
        System.out.print("логин: ");
        String login = scanner.nextLine();
        System.out.print("пароль: ");
        String password = scanner.nextLine();
        if (storage.logins.containsKey(login)) {
            String truePassword = storage.logins.get(login);
            if (password.equals(truePassword)) {
                System.out.println("добро пожаловать");
                return login;
            } else {
                System.out.println("пароль неверный");
            }
        } else {
            System.out.println("пользователь не найден");
        }
        return "";
    }

    public static void popolnenie(Scanner scanner, Storage storage, String login) {
        System.out.println("введите сумму для пополнения");
        int summa = scanner.nextInt();
        int balance = storage.balance.get(login);
        storage.balance.put(login, balance + summa);
    }

    public static void snyatie(Scanner scanner, Storage storage, String login) {
        System.out.println("введите сумму для снятия ");
        int summa = scanner.nextInt();
        int balance = storage.balance.get(login);
        if (summa > 0 && balance >= summa) {
            storage.balance.put(login, balance - summa);
        } else {
            System.out.println("ошибка");
        }
    }

    public static void perevod(Scanner scanner, Storage storage, String login) {
        System.out.println("введите логин пользователя");
        scanner.nextLine();
        String perevod = scanner.nextLine();
        if (storage.logins.containsKey(perevod)) {
            System.out.println("введите сумму перевода");
            int summa = scanner.nextInt();
            int balance = storage.balance.get(login);
            if (summa > 0 && balance >= summa) {
                int balance2 = storage.balance.get(perevod);
                storage.balance.put(login, balance - summa);
                storage.balance.put(perevod, balance2 + summa);
            }
        }
    }
}