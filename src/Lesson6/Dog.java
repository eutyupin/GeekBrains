package Lesson6;

public class Dog extends Animal{
    private final String type;
    public static int dogCount = 0;

    public Dog(String name,String type, String color, int age) {
        super(name, color, age);
        this.type = type;
        dogCount++;
    }

    @Override
    public void run(int meters){
        int MAX_RUN_DISTANCE = 500;
        if(meters <= MAX_RUN_DISTANCE) System.out.printf("Собака %s пробежала %d метров\n", name, meters);
        else System.out.printf("Собака не может бежать больше %d метров\n", MAX_RUN_DISTANCE);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void swim(int meters){
        int MAX_SWIM_DISTANCE = 10;
        if(meters <= MAX_SWIM_DISTANCE) System.out.printf("Собака %s проплыла %d метров\n", name, meters);
        else System.out.println("Собака " + name + " не умеет плавать более "+ MAX_SWIM_DISTANCE + " меров!");
    }

    @Override
    public String toString() {
        return "Собака {" +
                "имя = '" + name + '\'' +
                ", порода = '" + type + '\'' +
                ", цвет = '" + color + '\'' +
                ", возраст = " + age +
                '}';
    }

    public String getType() {
        return type;
    }
}
