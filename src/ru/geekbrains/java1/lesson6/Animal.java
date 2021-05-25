package ru.geekbrains.java1.lesson6;

public abstract class Animal {
    protected final String name;
    protected final String color;
    protected final int age;
    public static int animalCount = 0;

    public Animal(String name, String color, int age){
        this.name = name;
        this.color = color;
        this.age = age;
        animalCount++;
    }

    public abstract void swim(int meters);
    public abstract void run(int meters);
    public abstract String getName();
    public abstract String getColor();
    public abstract  int getAge();

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}
