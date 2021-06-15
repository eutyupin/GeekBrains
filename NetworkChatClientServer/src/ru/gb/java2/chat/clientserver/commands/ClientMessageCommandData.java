package ru.gb.java2.chat.clientserver.commands;

import java.io.Serializable;

public class ClientMessageCommandData implements Serializable {
    private final String sender;
    private final String message;
    private final Flag flag;

    public ClientMessageCommandData(Flag flag, String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.flag = flag;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public Flag getFlag() {
        return flag;
    }
}
