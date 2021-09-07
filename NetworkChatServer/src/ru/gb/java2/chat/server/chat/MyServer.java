package ru.gb.java2.chat.server.chat;

import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.Flag;
import ru.gb.java2.chat.server.ServerApp;
import ru.gb.java2.chat.server.chat.auth.AuthService;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {
    private List<ClientHandler> clients = new ArrayList<>();
    private AuthService authService;
    public Logger logger = LoggerFactory.getLogger(ServerApp.class);

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("Server has been started");
            authService = new AuthService();
            while (true) {
                waitAndProcessNewClientConnection(serverSocket);
            }

        }catch (IOException e) {
            logger.error("Failed to bind port" + port);
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
        logger.info("Waiting for new client connection...");
        Socket clientSocket =  serverSocket.accept();
        logger.info("Client has been connected");
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendCommand(Command.clientMessageCommand(Flag.ALL, sender.getUserName(),message));
                logger.info("Client " + sender.getUserName() + " send message to all users");
            }
        }
    }

    public synchronized void sendPrivateMessage(ClientHandler sender, String receiver, String message) throws IOException{
        for (ClientHandler client : clients) {
            if (client.getUserName().equals(receiver)) {
                client.sendCommand(Command.clientMessageCommand(Flag.PRIVATE, sender.getUserName(), message));
                logger.info("Client " + sender.getUserName() + " send private message to " + receiver);
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
        logger.info("Client " + clientHandler.getUserName() + " was subscribed to server");
        sendUserList();

    }
    public synchronized void unsubscribe(ClientHandler clientHandler) throws IOException {
        clients.remove(clientHandler);
        clientHandler.esServer.shutdown();
        logger.info("Client " + clientHandler.getUserName() + " was unsubscribed from server");
        sendUserList();
    }

}
