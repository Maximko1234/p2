package People;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorkMain {

    public static void main(String[] args) {
        ArrayList<Person> people = PersonGenerator.generatePeople(100);
        PersonGenerator.printPeople(people);

        // Задача 1: Найти самого взрослого человека
        int max = 0;
        Person oldest = null;

        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getAge() > max) {
                max = people.get(i).getAge();
                oldest = people.get(i);
            }
        }

        if (oldest != null) {
            System.out.println("\nСамый взрослый человек: " + oldest);
        }

        // Задача 2: Найти средний возраст людей
        int sum = 0;

        for (int i = 0; i < people.size(); i++) {
            sum = sum + people.get(i).getAge();
        }

        int MaxAge = sum / people.size();
        System.out.println("\nСредний возраст людей: " + MaxAge);

        // Задача 3: Посчитать сколько людей из каждого города
        Map<String, Integer> cityCount = new HashMap<>();

        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            String city = p.city;

            if (cityCount.containsKey(city)) {
                int ZCount = cityCount.get(city);
                cityCount.put(city, ZCount + 1);
            } else {
                cityCount.put(city, 1);
            }
        }

        System.out.println("\nКоличество людей из каждого города:");
        for (String city : cityCount.keySet()) {
            int count = cityCount.get(city);
            System.out.println(city + ": " + count + " человек(а)");
        }

        // Задача 4: Найти самый населенный город
        String PCity = null;
        int maxCount = 0;

        for (String city : cityCount.keySet()) {
            int count = cityCount.get(city);
            if (count > maxCount) {
                maxCount = count;
                PCity = city;
            }
        }

        System.out.println("\nСамый населенный город: " + PCity +
                " (" + maxCount + " человек)");

        // Задача 5: Группировка людей по городам
        Map<String, ArrayList<Person>> map = new HashMap<>();

        for (Person person : people) {
            ArrayList<Person> persons = map.get(person.city);
            if (persons == null) {
                persons = new ArrayList<>();
                map.put(person.city, persons);
            }
            persons.add(person);
        }

        System.out.println("\nЛюди по городам:");
        for (String city : map.keySet()) {
            ArrayList<Person> cityPeople = map.get(city);
            System.out.println(city + ": " + cityPeople.size() + " человек");
        }
    }
}