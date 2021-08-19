package ru.gb.java2.chat.clientserver.commands;

import java.io.Serializable;

public class ChangeUserNameCommand implements Serializable {

    private final String newUserName;
    private final String oldUserName;

    public ChangeUserNameCommand(String oldUserName, String newUserName) {
        this.oldUserName = oldUserName;
        this.newUserName = newUserName;
    }

    public String getNewUserName() {
        return newUserName;
    }
    public String getOldUserName() {
        return oldUserName;
    }
}
