package Man;

import lesson_class.Person;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManMap {
    public static void main(String[] args) {
        Person p1 = new Person("Bob", 23, "USA");
        Person p2 = new Person("Alice", 10, "USA");
        Person p3 = new Person("Andrey", 12, "Canada");
        Person p4 = new Person("Igor", 50, "Russia");

        Map<String, Integer> bomb = new HashMap<>();
        bomb.put("Bob", 0);
        bomb.put("Alice", 0);
        bomb.put("Andrey", 0);
        bomb.put("Igor", 0);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду: \n1 - добавить гол\n2 - добавить игрока\n3 - выйти");
            Integer command = scanner.nextInt();

            if (command == 1) {
                System.out.println("Введите имя футболиста: ");
                String name = scanner.next();

                if (bomb.containsKey(name)) {
                    Integer goals = bomb.get(name);
                    bomb.put(name, goals + 1);
                    System.out.println("Гол добавлен игроку " + name + "! Количество голов у игрока " + bomb.get(name));
                } else {
                    System.out.println("Футболист не найден");
                }
            } else if (command == 2) {
                System.out.println("Введите имя футболиста: ");
                String name = scanner.next();
                if (!bomb.containsKey(name)) {
                    bomb.put(name, 0);
                    System.out.println("Игрок " + name + " успешно добавлен");
                } else {
                    System.out.println("Игрок " + name + " уже существует");
                }
            } else {
                break;
            }
        }

        System.out.println(bomb);

        String winner = "";
        Integer max = 0;
        for (String name : bomb.keySet()) {
            if (bomb.get(name) > max) {
                max = bomb.get(name);
                winner = name;
            }
        }

        System.out.println("Победил " + winner + "! Забитых голов " + max);
    }
}

