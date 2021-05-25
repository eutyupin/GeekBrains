package ru.geekbrains.java2.lesson1.v2badcodeinstanceof;

public class Robot implements Movements{
    private final String NAME;
    private final OS OS;
    private final double MAX_RUN_DISTANCE;
    private final double MAX_JUMP_HEIGHT;
    private boolean nextStep = true;

    public Robot(String name, OS OS, double max_run_distance, double max_jump_height) {
        this.NAME = name;
        this.OS = OS;
        this.MAX_RUN_DISTANCE = max_run_distance;
        this.MAX_JUMP_HEIGHT = max_jump_height;
    }

    @Override
    public void makeMovement(Barrier barrier) {
        if(barrier instanceof Wall) jump(barrier);
        if(barrier instanceof Treadmill) run(barrier);
    }

    @Override
    public void run(Barrier barrier) {
        Treadmill treadmill = (Treadmill) barrier;
        if (treadmill.getLength() <= MAX_RUN_DISTANCE) {
            System.out.println(toString() + " пробежал по дорожке длиной " + treadmill.getLength());
        } else {
            System.out.println(toString() + " не смог пробежать такую дистанцию " + treadmill.getLength() +
                    ". Максимально возможная дистанция для него " + MAX_RUN_DISTANCE);
            nextStep = false;
        }

    }

    @Override
    public void jump(Barrier barrier) {
        Wall wall = (Wall) barrier;
        if (wall.getHeight() <= MAX_JUMP_HEIGHT) {
            System.out.println(toString() + " перепрыгнул стену высотой " + wall.getHeight());
        } else {
            System.out.println(toString() + " не смог перепрыгнуть стену " + wall.getHeight() +
                    ". Максимально возможная высота для него " + MAX_JUMP_HEIGHT);
            nextStep = false;
        }
    }

    @Override
    public String toString() {
        return "Робот " + NAME + " ОС " + OS;
    }

    public String getName() {
        return NAME;
    }

    public OS getOS() {
        return OS;
    }

    public double getMAX_RUN_DISTANCE() {
        return MAX_RUN_DISTANCE;
    }

    public double getMAX_JUMP_HEIGHT() {
        return MAX_JUMP_HEIGHT;
    }

    public boolean isNextStep() {
        return nextStep;
    }

}
