package socialNetwork;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserStorage {

    private Map<Integer, Person> users;
    private Map<String, String> logins;
    private String razdel = "<s>";

    public UserStorage() {

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

                Person person = new Person();
                result.put(id, person);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }


}
