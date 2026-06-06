package socialNetwork;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Нажмите кнопку, чтобы:
                    1 - Добавить нового пользователя
                    2 - Добавить друга
                    3 - 
                    4 - 
                    5 - выход
                    6 -""");
            System.out.print("Введите команду ");
            int knopka = scanner.nextInt();
            if (knopka == 1) {

            } else if (knopka == 2) {

            } else if (knopka == 3) {

            } else if (knopka == 5) {
                System.out.println("До свидания!");
                break;
            }
    }

        storage.save();
    }
}
