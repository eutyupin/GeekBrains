package Java2.Lesson1;

public class Treadmill extends Barrier {
    private final double LENGTH;

    public Treadmill(double length) {
        this.LENGTH = length;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public String toString() {
        return ("Беговая дорожка длиной " + LENGTH + "м");
    }

    public double getLength() {
        return LENGTH;
    }

}
