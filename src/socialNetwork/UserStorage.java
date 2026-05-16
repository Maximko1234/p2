package socialNetwork;

import java.io.File;
import java.time.LocalDate;
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
                String lastName = data[2];
                String userName = data[3];
                String age = data[4];
                LocalDate birthday = LocalDate.parse(data[5]);

                Person person = new Person();
                result.put(id, person);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Произошла ошибка чтения из файла " + e.getMessage());
        }
    }


}
