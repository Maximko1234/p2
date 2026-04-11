package class_animal;


public class Animals {
    public static void main(String[] args) {
        Animal cat = new Animal("Musa", 1, "white");
        Animal dog = new Animal("Bobik", 2, "black");
        System.out.println(cat);
        AnimalUtils utils = new AnimalUtils();
        String olderAnimal = AnimalUtils.olderAnimal(cat,dog);
        System.out.println(olderAnimal + " самый старший");

        AnimalStorage animalStorage = new AnimalStorage();
        System.out.println(animalStorage.addAnimal(cat));

    }
}
