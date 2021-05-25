package ru.geekbrains.java1.lesson1;

public class HomeWorkApp {
    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }
    private static void printThreeWords(){
        System.out.println("Orange\nBanana\nApple");
    }
    private static void checkSumSign(){
        int a = 3;
        int b = 5;
        if ((a+b) >= 0){
            System.out.println("Сумма положительная");
        } else System.out.println("Сумма отрицательная");
    }
    private static void printColor(){
        int value = 45;
        if (value <= 0){
            System.out.println("Красный");
        } else if (value > 0 && value<= 100){
            System.out.println("Желтый");
        } else if (value > 100){
            System.out.println("Зеленый");
        }
    }
    private static void compareNumbers(){
        int a = 5;
        int b = 6;
        if (a >= b){
            System.out.println("a >= b");
        } else System.out.println("a < b");
    }
}
