package ru.gb.java2.chat.server.chat.auth;

import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.ChangeUserNameStatusCommand;
import ru.gb.java2.chat.clientserver.commands.Flag;
import ru.gb.java2.chat.server.chat.MyServer;

import java.sql.*;

public class AuthService {
    private MyServer server;
    private final String DB_URL = "jdbc:sqlite:C:/Users/Jeka/IdeaProjects/GeekBrains/NetworkChatServer/db/chatServer.db";

    public String getUserNameByLoginAndPassword(String login, String password) {
        String userName = "";
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();
            String queryString = String.format("SELECT username FROM logins WHERE user_login = \'%s\' AND user_password = \'%s\'", login, password);
            ResultSet resultSet = statement.executeQuery(queryString);
            userName = resultSet.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userName;
    }

    public Command changeUserName(String oldUserName, String newUserName) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();
            String queryString = String.format("UPDATE logins SET username = \'%s\' WHERE username = \'%s\' ", newUserName, oldUserName);
            int rows = statement.executeUpdate(queryString);
            if (rows > 0) {
                return Command.changeUserNameStatusCommand("Все ОК!",Flag.OK);
            } else return Command.changeUserNameStatusCommand("Ошибка!", Flag.ERROR);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Command.changeUserNameStatusCommand(throwables.getMessage(),Flag.ERROR);
        }
    }
}