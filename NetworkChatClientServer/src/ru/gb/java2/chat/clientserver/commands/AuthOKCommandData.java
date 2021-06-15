package ru.gb.java2.chat.clientserver.commands;

import java.io.Serializable;

public class AuthOKCommandData implements Serializable {
    private final String userName;

    public AuthOKCommandData(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
