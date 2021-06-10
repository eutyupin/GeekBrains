package ru.geekbrains.java2.lesson7.NetworkChatServer.src.ru.gb.java2.chat.server.chat;

import ru.gb.java2.chat.server.chat.auth.AuthService;
import ru.gb.java2.chat.server.chat.auth.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private List<ClientHandler> clients = new ArrayList<>();
    private AuthService authService;
    private static final String USER_LIST_PREFFIX = "/ul";
    private static final String USER_PRIVATE_MESSAGE = "/w";
    public static final String TO_ALL_USERS = "Все пользователи";
    public void start(int port) {

        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server has been started");
            authService = new AuthService();

            while (true) {
                waitAndProcessNewClientConnection(serverSocket);
            }
        }catch (IOException e) {
            System.err.println("Failed to bind port" + port);
            e.printStackTrace();
        }
    }

    public void sendUserListForClients() throws  IOException{
        String tempUserList = USER_LIST_PREFFIX + ",Все пользователи";
        for (ClientHandler client : clients) {
            tempUserList += "," + client.getUserName();
        }
        for (ClientHandler client : clients) {
            client.sendMessage(tempUserList);
        }
    }

    private void waitAndProcessNewClientConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for new client connection...");
        Socket clientSocket =  serverSocket.accept();
        System.out.println("Client has been connected");
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public void sendPrivateMessage(String message) throws IOException{
        String[] tmpMessage = message.split(",");
        String sender = tmpMessage[1];
        String receiver = tmpMessage[2];
        String splitedMessage = tmpMessage[3];
        for (ClientHandler client : clients) {
            if (client.getUserName().equals(receiver)) {
                client.sendMessage(String.join(",",USER_PRIVATE_MESSAGE,sender,splitedMessage));
            }

        }
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }
    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

}
