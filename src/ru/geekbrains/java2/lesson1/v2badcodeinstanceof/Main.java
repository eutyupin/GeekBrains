package ru.geekbrains.java2.lesson1.v2badcodeinstanceof;

public class Main {

    public static Object[] competitors = new Object[3];
    public static Barrier[] barrier = new Barrier[4];

    public static void main(String[] args) {
        competitors[0] = new Man("Сергей",25,900,1);
        competitors[1] = new Cat("Barsik",Color.BLACK,300,1.5);
        competitors[2] = new Robot("Verter",OS.UNIX,125,1);

        barrier[0] = new Wall(1.3);
        barrier[1] = new Treadmill(228);
        barrier[2] = new Wall(0.9);
        barrier[3] = new Treadmill(551);

        startMovings();
    }

    public static void startMovings() {
        for (Object competitor : competitors) {
            for (Barrier value : barrier) {
                if (competitor instanceof Man && ((Man) competitor).isNextStep()) {
                    ((Man) competitor).makeMovement(value);
                }
                if (competitor instanceof Cat && ((Cat) competitor).isNextStep()) {
                    ((Cat) competitor).makeMovement(value);
                }
                if (competitor instanceof Robot && ((Robot) competitor).isNextStep()) {
                    ((Robot) competitor).makeMovement(value);
                }
            }
        }
    }
}
