package ru.geekbrains.java2.lesson1.v2badcodeinstanceof;

public class Wall extends  Barrier {
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

}
