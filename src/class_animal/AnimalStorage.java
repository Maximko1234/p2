package class_animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalStorage {

    private List<Animal> AnimalList;
    private Map<Integer, Animal> AnimalMap;
    private int count;

    public AnimalStorage() {
        AnimalList = new ArrayList<>();
        AnimalMap = new HashMap<>();
        count = 0;
    }

    public int addAnimal(Animal a) {
        count = count + 1;
        AnimalList.add(a);
        AnimalMap.put(count, a);

        return count;
    }
}