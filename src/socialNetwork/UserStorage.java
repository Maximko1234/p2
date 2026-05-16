package socialNetwork;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class UserStorage {

    private Map<Integer, Person> users;
    private Map<String, String> logins;
    private String razdel = "<s>";

    public UserStorage() {
        this.users = getUsers();
        this.logins = getLogins();
    }

    private Map<String, String> getLogins() {

    }

    public void save() {

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

    private List<Integer> parseId(String ids){
        String[] idString = ids.split(",");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < idString.length; i++) {
            result.add(Integer.parseInt(ids));
        }

        return result;
    }

}
