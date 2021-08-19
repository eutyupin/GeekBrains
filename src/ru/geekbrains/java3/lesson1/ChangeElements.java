package ru.geekbrains.java3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChangeElements {
    private static Integer[] targetList = new Integer[10];
    private static Integer[] targetArray = new Integer[10];
    public static void main(String[] args) {
        setTargetLists(10);
        changeListElements(targetList, 3, 5);
        System.out.println(Arrays.toString(targetList));
        System.out.println(arrayConvertToList(targetArray));


    }
    // Задание 1
    private static <T> void changeListElements(T[] array, int index1, int index2) {
       T tempValue = array[index1];
       array[index1] = array[index2];
       array[index2] = tempValue;
    }
    // Задание 2
    private static <E> ArrayList<E> arrayConvertToList(E[] receivedArray) {
        ArrayList<E> createdArrayList = new ArrayList<>();
        for (E item:receivedArray) createdArrayList.add(item);
        return createdArrayList;
    }

    private static void setTargetLists(Integer count) {
        for (int i = 0; i < count; i++) targetList[i] = i;
        for (int i = count - 1; i >= 0; i--) targetArray[(count - 1) - i] = (Integer) i;
    }
}
