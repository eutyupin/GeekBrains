package ru.geekbrains.java3;

import java.util.ArrayList;
import java.util.Collections;

public class ChangeElements {
    private static ArrayList<Integer> targetList = new ArrayList<>();
    private static Integer[] targetArray = new Integer[10];
    public static void main(String[] args) {
        setTargetLists(10);
        System.out.println(changeListElements(targetList, 3, 5));
        System.out.println(arrayConvertToList(targetArray));


    }
    // Задание 1
    private static <E> ArrayList<E> changeListElements(ArrayList<E> oldList, int index1, int index2) {
       ArrayList<E> newList = new ArrayList<>();
       newList.addAll(0, oldList);
       E tempValue = newList.get(index1);
       newList.set(index1, newList.get(index2));
       newList.set(index2, tempValue);
       return newList;
    }
    // Задание 2
    private static <E> ArrayList<E> arrayConvertToList(E[] receivedArray) {
        ArrayList<E> createdArrayList = new ArrayList<>();
        for (E item:receivedArray) createdArrayList.add(item);
        return createdArrayList;
    }

    private static void setTargetLists(Integer count) {
        for (int i = 0; i < count; i++) targetList.add(i);
        for (int i = count - 1; i >= 0; i--) targetArray[(count - 1) - i] = (Integer) i;
    }
}
