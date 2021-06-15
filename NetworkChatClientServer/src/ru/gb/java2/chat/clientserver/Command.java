package ru.gb.java2.chat.clientserver;

import ru.gb.java2.chat.clientserver.commands.*;

import java.io.Serializable;
import java.util.List;

public class Command implements Serializable {
    private Object data;
    private CommandType type;


    public static Command authCommand(String login, String password) {
        Command command = new Command();
        command.data = new AuthCommandData(login, password);
        command.type = CommandType.AUTH;
        return  command;
    }

    public static Command authOKCommand(String userName) {
        Command command = new Command();
        command.data = new AuthOKCommandData(userName);
        command.type = CommandType.AUTH_OK;
        return  command;
    }

    public static Command errorCommand(String message) {
        Command command = new Command();
        command.data = new ErrorCommandData(message);
        command.type = CommandType.ERROR;
        return  command;
    }

    public static Command privateMessageCommand(String receiver, String message) {
        Command command = new Command();
        command.data = new PrivateMessageCommandData(receiver, message);
        command.type = CommandType.PRIVATE_MESSAGE;
        return  command;
    }

    public static Command publicMessageCommand(String message) {
        Command command = new Command();
        command.data = new PublicMessageCommandData(message);
        command.type = CommandType.PUBLIC_MESSAGE;
        return  command;
    }

    public static Command clientMessageCommand(Flag flag, String sender, String message) {
        Command command = new Command();
        command.data = new ClientMessageCommandData(flag, sender, message);
        command.type = CommandType.CLIENT_MESSAGE;
        return  command;
    }

    public static Command updateUserListCommand(List<String> users) {
        Command command = new Command();
        command.type = CommandType.UPDATE_USER_LIST;
        command.data = new UpdateUserListCommandData(users);
        return command;
    }

    public Object getData() {
        return data;
    }

    public CommandType getType() {
        return type;
    }
}
