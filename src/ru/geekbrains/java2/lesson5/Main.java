package ru.geekbrains.java2.lesson5;

public class Main {
    static final int size = 10000000;
    static final int part = size / 2;
    static float[] bigArray = new float[size];
    static float[] partedArrayFirst = new float[part];
    static float[] partedArraySecond = new float[part];
    static long startMillisValue;
    static long endMillisValue;

    public static void main(String[] args) {
        calculate();
    }
    private static void printResult() {
        System.out.print("Действие заняло: " + (endMillisValue - startMillisValue) + " миллисекунд.\nИли " +
                (((float)(endMillisValue - startMillisValue))/1000) + " секунд.\n");
    }

    private static void calculate() {
        System.out.println("Цикл-->");
        startMillisValue = System.currentTimeMillis();
        loopFilling(bigArray);
        endMillisValue = System.currentTimeMillis();
        printResult();
        System.out.println("Потоки-->");
        startMillisValue = System.currentTimeMillis();
        threadFilling();
        endMillisValue = System.currentTimeMillis();
        printResult();
    }

    private static void loopFilling(float[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void threadFilling() {
        System.arraycopy(bigArray,0,partedArrayFirst,0,part);
        System.arraycopy(bigArray,part,partedArraySecond,0,part);

        Thread threadFirst = new Thread( () -> {
            loopFilling(partedArrayFirst);
        });
        Thread threadSecond = new Thread(() -> {
            loopFilling(partedArraySecond);
        });

        System.arraycopy(partedArrayFirst,0,bigArray,0,part);
        System.arraycopy(partedArraySecond,0,bigArray,part,part);

    }

}
