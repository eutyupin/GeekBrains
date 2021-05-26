package ru.geekbrains.java2.lesson3.phonebook;

public class NoSuchSurnameException extends RuntimeException{
    public NoSuchSurnameException(String message) {
        super(message);
    }
}
