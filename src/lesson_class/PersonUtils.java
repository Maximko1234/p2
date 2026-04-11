package lesson_class;

public class PersonUtils {
    public String olderPerson(Person a, Person b) {
        if (a.getAge() > b.getAge()) {
            return a.getName();
        } else {
            return b.getName();
        }
    }

    public void birthday(Person a) {
        int age = a.getAge();
        int newAge = age + 1;
        a.setAge(newAge);
    }
}