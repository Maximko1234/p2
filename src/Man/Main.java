package Man;

import lesson_class.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Bob", 23, "USA");
        Person p2 = new Person("Alice", 10, "USA");
        Person p3 = new Person("Andrey", 32, "Canada");
        Person p4 = new Person("Igor", 50, "Russia");

        List<Person> listInv = new ArrayList<>();
        listInv.add(p1);
        listInv.add(p2);
        listInv.add(p3);
        listInv.add(p4);

        List<Person> children = new ArrayList<>();
        List<Person> adults = new ArrayList<>();

        for (Person person : listInv) {
            if (person.getAge() < 18) {
                children.add(person);
            } else {
                adults.add(person);
            }
        }

        System.out.println("Дети: " + children);
        System.out.println("Взрослые: " + adults);
    }
}