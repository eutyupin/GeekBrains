package Java2.Lesson1;

public class Wall extends  Barrier {
    private final double HEIGHT;

    public Wall(double height) {
        this.HEIGHT = height;
    }

    @Override
    public String toString() {
        return ("Стена высотой " + HEIGHT + "м");
    }

    public double getHeight() {
        return HEIGHT;
    }

    @Override
    public double getLength() {
        return 0;
    }

}
