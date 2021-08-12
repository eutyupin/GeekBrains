package ru.geekbrains.java3;

import java.util.ArrayList;

public class Box<E extends Fruit> {
    E currentFruit;
    private String boxName;
    private ArrayList<E> boxContent = new ArrayList<>();
    private String fruitType;

    public Box(String name) {
        this.boxName = name;
    }

    public void addFruit(E fruit) {
        if(boxContent.isEmpty()) {
            boxContent.add(fruit);
            currentFruit = fruit;
            System.out.printf("В коробке %s %d объектов %s\n", boxName, boxContent.size(), currentFruit.getType());
        }
        else {
            if (fruit.getType() == this.currentFruit.getType()) {
                boxContent.add(fruit);

                System.out.printf("В коробке %s %d объектов %s\n", boxName, boxContent.size(), currentFruit.getType());
            } else System.out.printf("Вы не можете добавить объект %s в корзину с объектами %s\n", fruit.getType(), currentFruit.getType());
        }
    }

    public float getWeight(){
        return boxContent.size() * currentFruit.getWeight();
    }

    public Integer getBoxSize() {
        return boxContent.size();
    }

    public ArrayList<E> getBoxFruits() {
        return boxContent;
    }

    public boolean compare(Box comparedBox) {
        if (comparedBox.getWeight() == this.getWeight()) return true;
        else return false;
    }

    public void boxClear(){
        boxContent.clear();
    }

    public String getBoxName() {
        return boxName;
    }

    public void pourFruits(Box destinationBox) {
        if(destinationBox.currentFruit.getType() == currentFruit.getType()) {
            boxContent.addAll(boxContent.size()-1, destinationBox.getBoxFruits());
            destinationBox.boxClear();
            System.out.printf("В коробке %s количество фруктов %d\n", destinationBox.getBoxName(), destinationBox.getBoxSize());
            System.out.printf("В коробке %s количество фруктов %d\n", this.getBoxName(), this.getBoxSize());
        }
        else {
            System.out.printf("Вы не можете пересыпать объекты %s в корзину с объектами %s\n", destinationBox.currentFruit.getType(), currentFruit.getType());
        }

    }

}
