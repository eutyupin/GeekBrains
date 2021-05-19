package Java1.Lesson6;

public class Cat extends Animal{
    private final String type;
    public static int catCount = 0;

    public Cat(String name, String type, String color, int age) {
        super(name, color, age);
        this.type = type;
        catCount++;
    }

    @Override
    public void run(int meters){
        int MAX_RUN_DISTANCE = 200;
        if(meters <= MAX_RUN_DISTANCE) System.out.printf("Кот %s пробежал %d метров\n", name, meters);
        else System.out.printf("Кот не может бежать больше %d метров\n", MAX_RUN_DISTANCE);
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
      System.out.println("Кот " + name + " не умеет плавать!");
    }

    @Override
    public String toString() {
        return "Кот {" +
                "имя = '" + getName() + '\'' +
                ", порода = '" + type + '\'' +
                ", цвет = '" + color + '\'' +
                ", возраст = " + age +
                '}';
    }
    public String getType() {
        return type;
    }

}
