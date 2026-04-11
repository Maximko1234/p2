import java.util.Scanner;
import java.util.InputMismatchException;

public class oshibka {
    public static void main(String[] args) {
        System.out.println("Калькулятор деления!");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите первое число: ");
            int a = scanner.nextInt();

            System.out.print("Введите второе число: ");
            int b = scanner.nextInt();

            try {
                System.out.println("Результат деления: " + a / b);
            } catch (ArithmeticException mathOshibka) {
                System.out.println("Делить на ноль нельзя!");
            }

            System.out.print("Введите еще раз второе число: ");
            b = scanner.nextInt();
            System.out.println("Результат деления: " + a / b);

        } catch (InputMismatchException oshibka) {
            System.out.println("Введено не число! Программа завершена.");
        }
    }
}