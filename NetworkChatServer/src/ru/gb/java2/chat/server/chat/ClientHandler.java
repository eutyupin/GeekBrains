package ru.gb.java2.chat.server.chat;

import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.*;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ClientHandler {
    private static final String USER_ERROR_COMMAND = "Некорректные логин или пароль";
    private static final String USER_ALREADY_EXIST = "Пользователь с таким именем уже авторизован";
    private static final int TIMEOUT_USER_AUTHORIZATION_SECONDS = 120;
    private MyServer server;
    private final Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private String userName;
    private String login;
    private String password;
    private Command command;
    private boolean userIsAuthorized = false;

    public ClientHandler(MyServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException{
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        new Thread(() -> {
            try {
                waitUserAuthorization(TIMEOUT_USER_AUTHORIZATION_SECONDS);
                authentication();
                readMessages();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Failed process message from client");
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to close connection");
                }
            }
        }).start();
    }

    private Command readCommand () throws IOException{
        Command command = null;
        try {
            command = (Command) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to read command class");
        }
        return command;
    }

    public void sendCommand(Command command) throws IOException{
        outputStream.writeObject(command);
    }

    private void authentication() throws IOException{

        while (true) {
            command = readCommand();
            if (command == null) continue;
            if(command.getType() == CommandType.AUTH) {
                AuthCommandData data = (AuthCommandData) command.getData();
                login = data.getLogin();
                password = data.getPassword();
            }
                userName = server.getAuthService().getUserNameByLoginAndPassword(login, password);
                if (userName == null || userName == "") {
                    sendCommand(Command.errorCommand(USER_ERROR_COMMAND));
                } else if(userAlreadyExist(userName)) {
                    sendCommand(Command.errorCommand(USER_ALREADY_EXIST));
                } else {
                    sendCommand(Command.authOKCommand(userName));
                    userIsAuthorized = true;
                    server.subscribe(this);
                    return;
                }

            }
    }
    /* таймаут отключения от сервера юзера, который не залогинился */
    private void waitUserAuthorization(int timeoutSeconds) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(!userIsAuthorized) closeConnection();
                    timer.cancel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, TimeUnit.SECONDS.toMillis(timeoutSeconds));
    }
    private boolean userAlreadyExist(String userName) {
        for (ClientHandler client : server.getClients()) {
            if(client.getUserName().equals(userName)) return true;
        }
        return false;
    }

    private void readMessages() throws IOException, ClassNotFoundException {
        while (true) {
            command = readCommand();
            if (command == null) continue;
            switch (command.getType()) {
                case PRIVATE_MESSAGE: {
                    PrivateMessageCommandData data = (PrivateMessageCommandData) command.getData();
                    server.sendPrivateMessage(this, data.getReceiver(), data.getMessage());
                    break; }
                case PUBLIC_MESSAGE: {
                    PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                    processMessage(data.getMessage());
                    break; }
                case CHANGE_USER_NAME: {
                    ChangeUserNameCommand data = (ChangeUserNameCommand) command.getData();
                    Command answer = server.getAuthService().changeUserName(data.getOldUserName(),data.getNewUserName());
                    server.sendCommand(this, answer);
                }
            }
        }
    }

    private void closeConnection() throws IOException {
        server.unsubscribe(this);
        clientSocket.close();
    }

    private void processMessage(String message) throws IOException {
        server.broadcastMessage(message, this);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
