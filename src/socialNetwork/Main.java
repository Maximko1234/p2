package socialNetwork;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Person admin = new Person("admin",
                "Oleg",
                "Ivanov",
                "Rostov",
                LocalDate.of(2001, 3, 20)
        );
        Person user = new Person("user123",
                "John",
                "Williams",
                "New-York",
                LocalDate.of(2005, 5, 20)
        );


    }
}
