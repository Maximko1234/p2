package files;
import People.Person;
import People.PersonGenerator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        writeFile();
        readListPeople();
        readFile();
        readNumbersFile();
        readWordsFile();
        task1();
        task2();
    }

    static void task1() {
        try {
            File file = new File("src/files/people.txt");
            Scanner scanner = new Scanner(file);
            Integer countWords = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                countWords += parts.length;
            }
            System.out.println("Total words: " + countWords);
            scanner.close();
        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }

    static void task2() {
        try {
            File file = new File("src/files/people.txt");
            Scanner scanner = new Scanner(file);

            String longestLine = "";
            int maxLength = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > maxLength) {
                    maxLength = line.length();
                    longestLine = line;
                }
            }

            System.out.println("Самая длинная строка: " + longestLine);
            System.out.println("Её длина: " + maxLength + " символов");

            scanner.close();

        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }

    static void readListPeople() {
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
            System.out.println(people);
            scanner.close();

        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }

    static void readFile() {
        try {
            File file = new File("people.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();

        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }

    static void writeFile() {
        try (FileWriter writer = new FileWriter("persons.txt")) {
            List<Person> persons = PersonGenerator.generatePeople(10);

            for (Person p : persons) {
                writer.write(p.name + " " + p.age + " " + p.city + "\n");
            }

        } catch (IOException e) {
            System.out.println("Произошла файловая ошибка " + e.getMessage());
        }
    }

    static void readNumbersFile() {
        try {
            File file = new File("numbers.txt");
            Scanner scanner = new Scanner(file);
            List<Integer> numbers = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNextInt()) {
                    int number = lineScanner.nextInt();
                    numbers.add(number);
                }
                lineScanner.close();
            }
            System.out.println(numbers);
            scanner.close();

        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }

    static void readWordsFile() {
        try {
            File file = new File("words.txt");
            Scanner scanner = new Scanner(file);
            List<String> words = new ArrayList<>();

            while (scanner.hasNext()) {
                String word = scanner.next();
                words.add(word);
            }

            System.out.println("Все слова: " + words);
            System.out.println("Количество слов: " + words.size());
            scanner.close();

        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }
}