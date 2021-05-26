package ru.geekbrains.java2.lesson3.phonebook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class PhoneBook {
    private HashMap<String, HashSet<String>> phonebook = new HashMap<>();


    public void add(String surname, String phone) {
        HashSet<String> phones = new HashSet<>();
            if(phonebook.containsKey(surname.toUpperCase())) {
                phones.addAll(phonebook.get(surname.toUpperCase()));
                phones.add(phone);
            }
        if(phones.isEmpty()) phones.add(phone);
        phonebook.put(surname.toUpperCase(), phones);
    }

    public HashMap<String,HashSet<String>> get(String surname) {
       HashMap<String, HashSet<String>> tmpMap = new HashMap<>();
       if(phonebook.containsKey(surname.toUpperCase())) {
           tmpMap.put(surname.toUpperCase(), phonebook.get(surname.toUpperCase()));
       } else throw new NoSuchSurnameException("Нет такой записи в телефонном справочнике");
//       else tmpMap.put("Нет такого человека в телефонном справочнике", null); //это если без исключения
       return tmpMap;
    }
}
