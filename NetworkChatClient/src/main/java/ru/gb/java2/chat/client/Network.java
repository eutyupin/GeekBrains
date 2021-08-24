package ru.gb.java2.chat.client;

import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.CommandType;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

public class Network {

    private static final int SERVER_PORT = 8010;
    private static final String SERVER_HOST = "localhost";

    private final String host;
    private final int port;
    private Socket socket;
    private ObjectInputStream socketInput;
    private ObjectOutputStream socketOutput;
    private MainChat mainChat;

    public Network(String host, int port) {
        this.host = "localhost";
        this.port = port;
    }

    public Network() {
        this(SERVER_HOST, SERVER_PORT);
    }

    public boolean connect() {
        try {
            socket = new Socket(host, port);
            socketOutput = new ObjectOutputStream(socket.getOutputStream());
            socketInput = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to establish connection");
            return false;
        }
    }

    public void sendPrivateMessage(String receiver, String message) throws IOException {
        sendCommand(Command.privateMessageCommand(receiver, message));
    }

    public void sendMessage(String message) throws IOException {
        sendCommand(Command.publicMessageCommand(message));
    }

    public void sendCommand (Command command) throws IOException {
        try {
            socketOutput.writeObject(command);
        } catch (IOException e) {
            System.err.println("Failed to send message to server");
            throw e;
        }
    }

    public void waitMessages(Consumer<Command> messageHandler){
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    Command command = readCommand();
                    if (command == null) continue;
                    messageHandler.accept(command);
                } catch (IOException e) {
                    System.err.println("Failed to read message from server");
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private Command readCommand () throws IOException{
        Command command = null;
        try {
            command = (Command) socketInput.readObject();
//            System.out.println("Command has read: " + command.getType());
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to read command class");
            e.printStackTrace();
        }
        return command;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAuthCommand(String login, String password) throws IOException {
        sendCommand(Command.authCommand(login, password));
    }
    public void setMainChat(MainChat mainChat) {
        this.mainChat = mainChat;
    }

    public void sendChangeUsernameCommand(String currentUserName, String newUsername) throws IOException {
        sendCommand(Command.changeNameCommand(currentUserName, newUsername));
    }
}
