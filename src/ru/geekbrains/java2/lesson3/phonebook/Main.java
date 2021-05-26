package ru.geekbrains.java2.lesson3.phonebook;

public class Main {
    public static PhoneBook phonebook = new PhoneBook();
    public static void main(String[] args) {
        phonebook.add("Ivanov","+79235225582");
        phonebook.add("Ivanov","+79235225583");
        phonebook.add("Ivanov","+79235225584");
        phonebook.add("Ivanov","+79235225585");
        phonebook.add("Petrov","+79235225522");
        phonebook.add("sidorov","+79235112522");
        phonebook.add("Sidorov","+79502558565");
        phonebook.add("PeTroV","+79999999999");
        phonebook.add("Volkov","+79289652322");
        getFromPhoneBook("ivanov");
    }

    public static void getFromPhoneBook(String surname) {
        try {
            System.out.println(phonebook.get(surname));
        }catch (NoSuchSurnameException e) {
            System.out.println(e.getMessage());
        }
    }
}
