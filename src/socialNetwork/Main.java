package socialNetwork;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();


        //Реализовать добавление друзей
        //1. Создавть челдовека. Создать еще одного. Добавить их в друзья


        storage.save();
        List<Person> personList = storage.getPersons();
        System.out.println("Список всех пользователей:");
        for (Person p : personList) {
            System.out.println(p);
        }
    }
}
