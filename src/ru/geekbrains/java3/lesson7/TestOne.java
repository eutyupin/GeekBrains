package ru.geekbrains.java3.lesson7;

import ru.geekbrains.java3.lesson7.annotations.AfterSuite;
import ru.geekbrains.java3.lesson7.annotations.BeforeSuite;
import ru.geekbrains.java3.lesson7.annotations.Test;

public class TestOne {

    @BeforeSuite
    public void testMethodBegin() {
        System.out.println("BeforeSuite annotation");
    }
    @Test
    public void testMethodOne(int value) {
        System.out.println("Test annotation with priority = " + value);
    }

    @Test (value = 1)
    public void testMethodTwo(int value) {
        System.out.println("Test annotation with priority = " + value);
    }

    @Test (value = 5)
    public void testMethodThree(int value) {
        System.out.println("Test annotation with priority = " + value);

    }

    @Test (value = 3)
    public void testMethodFour(int value) {
        System.out.println("Test annotation with priority = " + value);

    }

    @Test (value = 4)
    public void testMethodFive(int value) {
        System.out.println("Test annotation with priority = " + value);
    }

    @AfterSuite
    public void testMethodEnd() {
        System.out.println("AfterSuite annotation");
    }
}
