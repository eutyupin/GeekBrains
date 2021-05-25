package ru.geekbrains.java2.lesson1.v1goodcode;

public class Man implements Competitors {
    private final String NAME;
    private final int AGE;
    private final double MAX_RUN_DISTANCE;
    private final double MAX_JUMP_HEIGHT;
    private boolean nextStep = true;

    public Man(String name, int age, double max_run_distance, double max_jump_height) {
        this.NAME = name;
        this.AGE = age;
        this.MAX_RUN_DISTANCE = max_run_distance;
        this.MAX_JUMP_HEIGHT = max_jump_height;
    }

    @Override
    public String toString() {
        return "Человек " + NAME + ", возраст " + AGE;
    }

    public String getName() {
        return NAME;
    }

    public int getAge() {
        return AGE;
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

    public void setNextStep(boolean nextStep) {
        this.nextStep = nextStep;
    }
}
