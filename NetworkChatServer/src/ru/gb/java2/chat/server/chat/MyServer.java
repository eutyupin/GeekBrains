package ru.gb.java2.chat.server.chat;

import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.Flag;
import ru.gb.java2.chat.server.chat.auth.AuthService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private List<ClientHandler> clients = new ArrayList<>();
    private AuthService authService;

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

    public synchronized void sendUserList() throws  IOException {
        List<String> users = new ArrayList<>();
        for (ClientHandler client : clients) {
            users.add(client.getUserName());
        }
        for (ClientHandler client : clients) {
            client.sendCommand(Command.updateUserListCommand(users));
        }
    }

    private void waitAndProcessNewClientConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for new client connection...");
        Socket clientSocket =  serverSocket.accept();
        System.out.println("Client has been connected");
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendCommand(Command.clientMessageCommand(Flag.ALL, sender.getUserName(),message));
            }
        }
    }

    public synchronized void sendPrivateMessage(ClientHandler sender, String receiver, String message) throws IOException{
        for (ClientHandler client : clients) {
            if (client.getUserName().equals(receiver)) {
                client.sendCommand(Command.clientMessageCommand(Flag.PRIVATE, sender.getUserName(), message));
            }
        }
    }

    public List<ClientHandler> getClients() {
        return clients;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void subscribe(ClientHandler clientHandler) throws IOException {
        clients.add(clientHandler);
        sendUserList();

    }
    public synchronized void unsubscribe(ClientHandler clientHandler) throws IOException {
        clients.remove(clientHandler);
        sendUserList();
    }

}
