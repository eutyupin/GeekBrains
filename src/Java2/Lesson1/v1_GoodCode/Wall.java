package Java2.Lesson1.v1_GoodCode;

public class Wall implements Barrier {
    private final double HEIGHT;

    public Wall(double height) {
        this.HEIGHT = height;
    }

    @Override
    public String toString() {
        return ("Стена высотой " + HEIGHT + "м");
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    @Override
    public double getLength() {
        return 0;
    }

    @Override
    public void move(Competitors competitor) {
        if (getHeight() <= competitor.getMAX_JUMP_HEIGHT()) {
            System.out.println(competitor.toString() + " перепрыгнул стену высотой " + getHeight());
        } else {
            System.out.println(competitor.toString() + " не смог перепрыгнуть такую стену " + getHeight() +
                    ". Максимально возможная высота для него " + competitor.getMAX_JUMP_HEIGHT());
            competitor.setNextStep(false);
        }
    }

}
