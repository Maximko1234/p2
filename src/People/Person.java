package People;

public class Person {

    public String name;
    public int age;
    public String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return name + " (" + age + ") - " + city;
    }

    public int getAge() {

        return age;
    }}