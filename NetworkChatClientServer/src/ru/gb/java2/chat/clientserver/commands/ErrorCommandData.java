package ru.gb.java2.chat.clientserver.commands;

import java.io.Serializable;

public class ErrorCommandData implements Serializable {
    private final String ErrorMessage;

    public ErrorCommandData(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
