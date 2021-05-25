package ru.geekbrains.java2.lesson1.v1goodcode;

public interface Competitors {
    default void makeMovement(Barrier barrier) {
       barrier.move(this);
    }
    double getMAX_RUN_DISTANCE();
    double getMAX_JUMP_HEIGHT();
    boolean isNextStep();
    void setNextStep(boolean nextStep);
    String toString();
//    void run(Barrier barrier);
//    void jump(Barrier barrier);
}
