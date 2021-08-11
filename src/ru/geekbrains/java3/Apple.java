package ru.geekbrains.java3;

public class Apple implements Fruit{
    private float weight = 1.0f;
    private String type = "Apple";

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String getType() {
        return type;
    }
}
