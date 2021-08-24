package ru.gb.java2.chat.clientserver.commands;

import java.io.Serializable;

public enum CommandType implements Serializable {
    AUTH,
    AUTH_OK,
    ERROR,
    PUBLIC_MESSAGE,
    PRIVATE_MESSAGE,
    CLIENT_MESSAGE,
    UPDATE_USER_LIST,
    CHANGE_USER_NAME,
    CHANGE_USER_NAME_STATUS
}
