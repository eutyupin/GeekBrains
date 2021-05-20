package Java2.Lesson1.v1_GoodCode;

public class Cat implements Competitors {
    private final String NAME;
    private final Color COLOR;
    private final double MAX_RUN_DISTANCE;
    private final double MAX_JUMP_HEIGHT;
    private boolean nextStep = true;

    public Cat(String name, Color color, double max_run_distance, double max_jump_height) {
        this.NAME = name;
        this.COLOR = color;
        this.MAX_RUN_DISTANCE = max_run_distance;
        this.MAX_JUMP_HEIGHT = max_jump_height;
    }

    @Override
    public String toString() {
        return "Кот " + NAME + ", цвет " + COLOR;
    }

    public String getName() {
        return NAME;
    }

    public Color getColor() {
        return COLOR;
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
