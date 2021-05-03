package Lesson6;

public class Animal {
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

    public void swim(int meters){
        System.out.println("Животное " + name + "проплыло " + meters + " метров.");
    }
    public void run(int meters){
        System.out.println("Животное " + name + " пробежало " + meters + " метров.");
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}
