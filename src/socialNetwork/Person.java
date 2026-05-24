package socialNetwork;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    @Override
    public String toString() {
        return "Person{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", city='" + city + '\'' +
                ", friends=" + friends +
                '}';
    }

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

    public static int getNextId() {
        return nextId;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getCity() {
        return city;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public String getFrinedsString() {
        return friends.stream()
                .map(Object:: toString)
                .collect(Collectors.joining(","));
    }

    public void addFriend(int id) {
        friends.add(id);
    }
}
