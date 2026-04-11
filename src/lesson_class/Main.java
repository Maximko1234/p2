package lesson_class;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Sasha", 12, "Russia");
        Person p2 = new Person("Alisa", 11, "France");
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        PersonUtils personUtils = new PersonUtils();
        String olderPerson = personUtils.olderPerson(p1,p2);
        System.out.println(olderPerson + " самый старший");

        personUtils.birthday(p1);
        personUtils.birthday(p1);
        olderPerson = personUtils.olderPerson(p1, p2);
        System.out.println(olderPerson + " самый старший");

    }
}