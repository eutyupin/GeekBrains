package Java2.Lesson1.v1_GoodCode;

public class Robot implements Competitors {
    private final String NAME;
    private final Java2.Lesson1.v1_GoodCode.OS OS;
    private final double MAX_RUN_DISTANCE;
    private final double MAX_JUMP_HEIGHT;
    private boolean nextStep = true;

    public Robot(String name, Java2.Lesson1.v1_GoodCode.OS OS, double max_run_distance, double max_jump_height) {
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

    public Java2.Lesson1.v1_GoodCode.OS getOS() {
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
