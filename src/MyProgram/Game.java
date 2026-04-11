package MyProgram;

import java.util.Random;
import java.util.Scanner;

public class Game {

    // Метод для задержки
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Метод для печати с задержкой
    public static void printWithDelay(String text, int delay) {
        System.out.println(text);
        sleep(delay);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choices = {"Камень", "Ножницы", "Бумага"};

        boolean playAgain = true;

        printWithDelay("=== КАМЕНЬ-НОЖНИЦЫ-БУМАГА ===", 500);

        while (playAgain) {
            printWithDelay("\n1. Камень", 200);
            printWithDelay("2. Ножницы", 200);
            printWithDelay("3. Бумага", 200);

            int playerChoice;
            do {
                System.out.print("Выбери вариант (1-3): ");
                playerChoice = scanner.nextInt();
            } while (playerChoice < 1 || playerChoice > 3);

            int computerChoice = random.nextInt(3);

            printWithDelay("Твой выбор: " + choices[playerChoice - 1], 300);
            printWithDelay("Компьютер: " + choices[computerChoice], 300);

            // Пауза перед результатом для драмы
            sleep(1000);

            if (playerChoice - 1 == computerChoice) {
                printWithDelay("🤝 Ничья!", 500);
            } else if ((playerChoice == 1 && computerChoice == 1) ||
                    (playerChoice == 2 && computerChoice == 2) ||
                    (playerChoice == 3 && computerChoice == 0)) {
                printWithDelay("🎉 Ты победил!", 500);
            } else {
                printWithDelay("💻 Компьютер победил!", 500);
            }

            System.out.print("Сыграем ещё? (1-да, 0-нет): ");
            int answer = scanner.nextInt();
            playAgain = (answer == 1);
        }

        printWithDelay("Спасибо за игру! 👋", 500);
        scanner.close();
    }
}

