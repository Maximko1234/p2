package socialNetwork;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {

    private static int nextId = 0;

    private String login;
    private int id; //всегда уникальный

    private String firstName;
    private String lastName;
    private int age;
    private LocalDate birthday;
    private String city;

    private List<Integer> friends;

    //Конструктор для считывания существующих пользователей из файла
    public Person(String login, int id, String firstName, String lastName, int age, String city, LocalDate birthday, List<Integer> friends) {
        this.login = login;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthday = birthday;
        this.friends = friends;
        this.city = city;
    }

    //конструтор при создании нового пользователя
    public Person(String login, String firstName, String lastName, String city, LocalDate birthday) {
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.city = city;

        this.id = nextId;
        nextId += 1;
        this.friends = new ArrayList<>();
        this.age = LocalDate.now().getYear() - birthday.getYear();
    }

}
