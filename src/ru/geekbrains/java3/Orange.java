package ru.geekbrains.java3;

public class Orange implements Fruit{
    private float weight = 1.5f;
    private String type = "Orange";

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String getType() {
        return type;
    }
}
