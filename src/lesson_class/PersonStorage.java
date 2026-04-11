package lesson_class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonStorage {

    private List<Person> personList;
    private Map<Integer, Person> personMap;
    private int count;

    public PersonStorage() {
        personList = new ArrayList<>();
        personMap = new HashMap<>();
        count = 0;
    }

    public int addPerson(Person a) {
        count = count + 1;
        personList.add(a);
        personMap.put(count, a);

        return count;
    }
}