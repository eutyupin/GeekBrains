package ru.geekbrains.java2.lesson7.NetworkChatServer.src.ru.gb.java2.chat.server.chat.auth;

import java.util.Map;

public class AuthService {
    private static Map<User, String> USERS = Map.of(
            new User("login1", "pass1"), "username1",
            new User("login2", "pass2"),"username2",
            new User("login3", "pass3"), "username3");

    public String getUserNameByLoginAndPassword(String login, String password) {
        return USERS.get(new User(login, password));
    }
}
