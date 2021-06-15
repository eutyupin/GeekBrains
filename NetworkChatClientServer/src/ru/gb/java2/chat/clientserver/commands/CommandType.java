package ru.gb.java2.chat.clientserver.commands;

public enum CommandType {
    AUTH,
    AUTH_OK,
    ERROR,
    PUBLIC_MESSAGE,
    PRIVATE_MESSAGE,
    CLIENT_MESSAGE,
    UPDATE_USER_LIST
}
