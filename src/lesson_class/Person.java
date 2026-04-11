package lesson_class;

public class Person {

    String name;
    int age;
    String country;

    public Person(String userName, int userAge, String userCountry) {
        name = userName;
        age = userAge;
        country = userCountry;
    }
    

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }
    @Override
    public String toString() {
        return name + ", возраст " + age + ". Живет в " + country;
    }

    public void setAge(int newAge) {
    }
}
