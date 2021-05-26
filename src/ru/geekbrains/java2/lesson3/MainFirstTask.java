package ru.geekbrains.java2.lesson3;

import ru.geekbrains.java2.lesson3.phonebook.NoSuchSurnameException;
import ru.geekbrains.java2.lesson3.phonebook.PhoneBook;

import java.util.*;

public class MainFirstTask {

    private static ArrayList<String> wordsList = new ArrayList<>(Arrays.asList("one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine", "ten", "ten","ten","ten","two","two","four","four","four","four"));


    public static void main(String[] args) {
        System.out.println(fillUniqueWords(wordsList));
        System.out.println(wordsRepeatCount(wordsList));

    }

    private static LinkedHashSet<String> fillUniqueWords(ArrayList<String> receivedList) {
        LinkedHashSet<String> tempSet = new LinkedHashSet<>();
        for(String item : receivedList) tempSet.add(item);
        return tempSet;
    }

    private static LinkedHashMap<String, Integer> wordsRepeatCount(ArrayList<String> receivedList) {
        LinkedHashMap<String, Integer> tempMap = new LinkedHashMap<>();
            for (String item : receivedList) {
                int count = tempMap.getOrDefault(item,0);
                System.out.println(count);
                count++;
                tempMap.put(item,count);
                }
        return tempMap;
    }

}
