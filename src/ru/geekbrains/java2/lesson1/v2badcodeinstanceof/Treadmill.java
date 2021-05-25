package ru.geekbrains.java2.lesson1.v2badcodeinstanceof;

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

    @Override
    public double getLength() {
        return LENGTH;
    }

}
