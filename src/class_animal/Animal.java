package class_animal;

public class Animal {

    String name;
    int age;
    String color;

    public Animal(String userName, int userAge, String usercolor) {
        name = userName;
        age = userAge;
        color = usercolor;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return color;
    }
    @Override
    public String toString() {
        return name + ", возраст " + age + ".окраска " + color;
    }

    public void setAge(int newAge) {
        age = newAge;
    }
}