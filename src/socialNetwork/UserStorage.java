package socialNetwork;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class UserStorage {

    private Map<Integer, Person> users;
    private Map<String, String> logins;
    private String razdel = "<s>";

    /*
     * Создает хранилище пользователей и логинов
     */
    public UserStorage() {
        this.users = getUsers();
        this.logins = getLogins();
    }

    private Map<String, String> getLogins() {

    }

    public void save() {
        System.out.println("Сохранение данных в файл...");
        try (FileWriter writer = new FileWriter("src/socialNetwork/storage/users.txt")) {
            for (var elem : users.entrySet()) {
                Person person = elem.getValue();
                writer.write(person.getId() + razdel +
                        person.getFirstName() + razdel +
                        person.getLastName() + razdel +

                        "\n");
            }
        } catch (IOException e) {
            System.out.println("Произошла файловая ошибка " + e.getMessage());
        }
    }

    private Map<Integer, Person> getUsers() {
        try {
            Map<Integer, Person> result = new HashMap<>();
            File file = new File("src/socialNetwork/storage/users.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(razdel);

                Integer id = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                String userName = data[3];
                Integer age = Integer.parseInt(data[4]);
                LocalDate birthday = LocalDate.parse(data[5]);
                List<Integer> friends = parseId(data[6]);
                String city = data[7];

                Person person =
                        new Person(userName, id, firstName, lastName, age, city, birthday, friends);
                result.put(id, person);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
            return null;
        }
    }

    /*
     * Парсит строку с ID в список
     * строка вида "1,2,3,4" -> [1, 2, 3, 4]
     */
    private List<Integer> parseId(String ids) {
        String[] idString = ids.split(",");

        List<Integer> result = new ArrayList<>();
        for (String element: idString) {
            result.add(Integer.parseInt(element));
        }

        return result;
    }

}
