package People;

import People.Person;

import java.util.ArrayList;
import java.util.Random;
public class PersonGenerator {


    public static ArrayList<Person> generatePeople(int count) {
        String[] names = {
                "Alex", "Maria", "Ivan", "Anna",
                "John", "Kate", "Max", "Olga",
                "Leo", "Sofia", "Denis", "Pau"
        };

        String[] cities = {
                "Madrid", "Berlin", "Paris",
                "Rome", "London", "Moscow"
        };

        Random random = new Random();
        ArrayList<Person> people = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String name = names[random.nextInt(names.length)];
            int age = random.nextInt(40) + 10;
            String city = cities[random.nextInt(cities.length)];

            people.add(new Person(name, age, city));
        }

        return people;
    }
    public static void printPeople(ArrayList<Person> people) {
        for (int i = 0; i <people.size() ; i++) {
            System.out.println(people.get(i));
        }
    }
}
