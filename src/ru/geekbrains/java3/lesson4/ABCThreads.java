package ru.geekbrains.java3.lesson4;

public class ABCThreads {
    private volatile String currentLetter = "A";

    public static void main(String[] args) {
        ABCThreads abcObject = new ABCThreads();

        new Thread( () -> {
            abcObject.printA();
        },"thread-A").start();

        new Thread(()->{
            abcObject.printB();
        }, "thread-B").start();

        new Thread(()->{
            abcObject.printC();
        }, "thread-C").start();

    }

    public synchronized void printA() {
        for (int i = 0; i < 5; i++) {
            try {
                while (!currentLetter.equals("A")) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.print("A");
        currentLetter = "B";
        this.notifyAll();
         }
    }
    public synchronized void printB() {
        for (int i = 0; i < 5; i++) {
            try {
                while (!currentLetter.equals("B")) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("B");
            currentLetter = "C";
            this.notifyAll();
        }
    }
    public synchronized void printC() {
        for (int i = 0; i < 5; i++) {
            try {
                while (!currentLetter.equals("C")) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("C");
            currentLetter = "A";
            this.notifyAll();
        }
    }
}
