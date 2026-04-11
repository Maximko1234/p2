package class_animal;

public class AnimalUtils {
    public static String olderAnimal(Animal a, Animal b) {
        if (a.getAge() > b.getAge()) {
            return a.getName();
        } else {
            return b.getName();
        }
    }

    public void birthday(Animal a) {
        int age = a.getAge();
        int newAge = age + 1;
        a.setAge(newAge);

    }



}