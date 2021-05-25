package ru.geekbrains.java2.lesson1.v1goodcode;

public class Treadmill implements Barrier {
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

    @Override
    public double getLength() {
        return LENGTH;
    }

    @Override
    public void move(Competitors competitor) {
        if (getLength() <= competitor.getMAX_RUN_DISTANCE()) {
            System.out.println(competitor.toString() + " пробежал по дорожке длиной " + getLength());
        } else {
            System.out.println(competitor.toString() + " не смог пробежать такую дистанцию " + getLength() +
                    ". Максимально возможная дистанция для него " + competitor.getMAX_RUN_DISTANCE());
            competitor.setNextStep(false);
        }
    }

}
