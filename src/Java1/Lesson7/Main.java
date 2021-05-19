package Java1.Lesson7;

public class Main {
    public static void main(String[] args) {
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("Мурзик", 15);
        cats[1] = new Cat("Барсик", 25);
        cats[2] = new Cat("Пушок", 35);
        cats[3] = new Cat("Васька", 25);
        cats[4] = new Cat("Черныш", 55);

//       Как вариант быстрого заполнения массива котов
//        for (int catIndex = 0; catIndex < cats.length; catIndex++){
//           cats[catIndex] = new Cat("Cat_" + catIndex + 1, ((catIndex + 1) + (catIndex + 1) * 4));
//       }

        Plate plate = new Plate(100);

        for (Cat cat : cats) {
            cat.eat(plate);
            plate.info();
        }


    }
}
