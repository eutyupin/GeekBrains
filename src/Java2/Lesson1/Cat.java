package Java2.Lesson1;

public class Cat implements Movements{
    private final String NAME;
    private final Color COLOR;
    private final boolean CAN_RUN;
    private final boolean CAN_JUMP;
    private final double MAX_RUN_DISTANCE;
    private final double MAX_JUMP_HEIGHT;
    private boolean nextStep = true;

    public Cat(String name, Color color, boolean can_run, boolean can_jump, double max_run_distance, double max_jump_height) {
        this.NAME = name;
        this.COLOR = color;
        CAN_RUN = can_run;
        CAN_JUMP = can_jump;
        if(!can_run) MAX_RUN_DISTANCE = 0; else MAX_RUN_DISTANCE = max_run_distance;
        if(!can_jump) MAX_JUMP_HEIGHT = 0; else MAX_JUMP_HEIGHT = max_jump_height;
    }
    public Cat(String name, Color color) {
        this.NAME = name;
        this.COLOR = color;
        CAN_RUN = false;
        CAN_JUMP = false;
        MAX_RUN_DISTANCE = 0;
        MAX_JUMP_HEIGHT = 0;
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

}
