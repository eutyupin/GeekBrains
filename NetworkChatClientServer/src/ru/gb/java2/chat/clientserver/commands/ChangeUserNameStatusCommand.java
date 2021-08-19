package ru.gb.java2.chat.clientserver.commands;

import java.io.Serializable;

public class ChangeUserNameStatusCommand implements Serializable {

    private final String message;
    private final Flag flag;

    public ChangeUserNameStatusCommand(String message, Flag flag) {
        this.message = message;
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public Flag getFlag() {
        return flag;
    }
}
