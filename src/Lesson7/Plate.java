package Lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
     this.food = food;
    }

    public void info() {
        System.out.printf("plate: %d\n", food);
    }
    public void decreaseFood(int amount){
        food -= amount;
    }
    public void increaseFood(int amount) {
        food += amount;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }
}
