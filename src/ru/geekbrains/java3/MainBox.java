package ru.geekbrains.java3;

public class MainBox {
    public static void main(String[] args) {
        Box<Fruit> orangeBox = new Box<>("Апельсины1");
        Box<Fruit> orangeBox2 = new Box<>("Апельсины2");
        Box<Fruit> appleBox = new Box<>("Яблоки1");
        Box<Fruit> appleBox2 = new Box<>("Яблоки2");

        boxAddFruits(orangeBox,30, new Orange());
        orangeBox.addFruit(new Apple());
        boxAddFruits(orangeBox2, 10, new Orange());
        orangeBox.pourFruits(orangeBox2);

        boxAddFruits(appleBox,28, new Apple());
        boxAddFruits(appleBox2, 12, new Apple());
        orangeBox.pourFruits(appleBox2);

        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox2.getWeight());
        System.out.println(appleBox.getWeight());
        System.out.println(appleBox2.getWeight());

        System.out.println(orangeBox.compare(appleBox));
        boxAddFruits(appleBox, 32, new Apple());
        System.out.println(orangeBox.compare(appleBox));



    }

    public static void boxAddFruits(Box box, Integer count, Fruit fruit){
        for (int i = 0; i < count; i++) box.addFruit(fruit);
    }
}
