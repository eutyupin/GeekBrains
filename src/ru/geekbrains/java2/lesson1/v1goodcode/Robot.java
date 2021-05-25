package ru.geekbrains.java2.lesson1.v1goodcode;

public class Robot implements Competitors {
    private final String NAME;
    private final ru.geekbrains.java2.lesson1.v1goodcode.OS OS;
    private final double MAX_RUN_DISTANCE;
    private final double MAX_JUMP_HEIGHT;
    private boolean nextStep = true;

    public Robot(String name, ru.geekbrains.java2.lesson1.v1goodcode.OS OS, double max_run_distance, double max_jump_height) {
        this.NAME = name;
        this.OS = OS;
        this.MAX_RUN_DISTANCE = max_run_distance;
        this.MAX_JUMP_HEIGHT = max_jump_height;
    }

    @Override
    public String toString() {
        return "Робот " + NAME + " ОС " + OS;
    }

    public String getName() {
        return NAME;
    }

    public ru.geekbrains.java2.lesson1.v1goodcode.OS getOS() {
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

    @Override
    public void setNextStep(boolean nextStep) {
        this.nextStep = nextStep;
    }

}
