import java.util.Scanner;
public class Lesson2 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("напишите своё имя: ");
        String name = scanner.nextLine();

        System.out.print("введите свой возраст: ");
        int age = scanner.nextInt();


        System.out.println("привет, " + name + "," + "твой возраст = " + age);

    }
}