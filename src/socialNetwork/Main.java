package socialNetwork;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();


        //Реализовать добавление друзей


        storage.save();
        List<Person> personList = storage.getPersons();
        System.out.println("Список всех пользователей:");
        for (Person p : personList) {
            System.out.println(p);
        }
    }
}
