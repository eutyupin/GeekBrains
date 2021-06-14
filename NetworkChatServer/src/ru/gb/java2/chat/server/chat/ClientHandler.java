package ru.gb.java2.chat.server.chat;

import ru.gb.java2.chat.server.chat.auth.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private static final String AUTH_OK_COMMAND = "/authOK";
    private static final String AUTH_COMMAND = "/auth";
    private static final String USER_PRIVATE_MESSAGE = "/w";
    private static final String TO_ALL_USERS = "Все пользователи";
    private static final String USER_ERROR_COMMAND = "Некорректные логин или пароль";
    private static final String USER_ALREADY_EXIST = "Пользователь с таким именем уже авторизован";
    private MyServer server;
    private final Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String userName;

    public ClientHandler(MyServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException{
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authentication();
                server.sendUserListForClients();
                readMessages();
            } catch (IOException e) {
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

    private void authentication() throws IOException{

        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(AUTH_COMMAND)) {
                String[] authParts = message.split(",");
                String login = authParts[1];
                String password = authParts[2];
                userName = server.getAuthService().getUserNameByLoginAndPassword(login, password);
                if (userName == null) {
                    sendMessage(USER_ERROR_COMMAND);
                } else if(userAlredyExist(userName)) {
                    sendMessage(USER_ALREADY_EXIST);
                } else {
                    sendMessage(String.format("%s %s", AUTH_OK_COMMAND, userName));
                    server.subscribe(this);
                    return;
                }
            }
        }
    }

    private boolean userAlredyExist(String userName) {
        for (ClientHandler client : server.getClients()) {
            if(client.getUserName().equals(userName)) return true;
        }
        return false;
    }

    private void readMessages() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            if (message.startsWith(USER_PRIVATE_MESSAGE)) {
                server.sendPrivateMessage(message);
            }else{
                processMessage(message);
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

    public void sendMessage(String message) throws IOException {
        outputStream.writeUTF(message);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
