import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            Integer a = 9;
            System.out.println(a / 0);
        } catch (ArithmeticException oshibka) {
            System.out.println("В коде произошла ошибка: " + oshibka.getMessage());
        }
        System.out.println("Что нибудь");

        try {  // Теперь внутри метода!
            List<Integer> list = new ArrayList<>();
            list.add(1);
            System.out.println(list.get(9));
        } catch (IndexOutOfBoundsException oshibka) {
            System.out.println("В коде произошла ошибка: " + oshibka.getMessage());
        }
    }
}