package socialNetwork;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();

        List<Person> personList = storage.getPersons();
        System.out.println("Список всех пользователей:");
        for (Person p : personList) {
            System.out.println(p);
        }

    }
}
