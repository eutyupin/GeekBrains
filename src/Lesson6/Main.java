package Lesson6;

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Murzik","British", "White", 2);
        Dog dog = new Dog("Rex","Staffard","Brown", 1);

        System.out.println(cat);
        System.out.println(dog);

        dog.run(300);
        dog.swim(7);
        cat.run(125);
        cat.swim(20);

        printCount();
    }
    public static void printCount(){
        System.out.printf("Количество объектов Cat: %d\nКоличество объектов Dog: %d\nКоличество объектов Animal: %d\n",
                Cat.catCount, Dog.dogCount, Animal.animalCount);
    }
}
