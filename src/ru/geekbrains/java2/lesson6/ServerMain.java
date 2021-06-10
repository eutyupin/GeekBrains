package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
    private static final int PORT = 8189;

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        try (ServerSocket chatServerSocket = new ServerSocket()) {
            chatServerSocket.bind(new InetSocketAddress("localhost", 8189));
            System.out.println("Server has been started, waiting for new connection...");
            Socket clientSocket = chatServerSocket.accept();
            System.out.println("Client is connected");

            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            readMessage(input);
            writeMessage(output);

        } catch (IOException e) {
            System.out.println("Failed to bind port " + PORT);
            e.printStackTrace();
        }
    }
    private static void readMessage (DataInputStream input) {
        new Thread(() -> {
        while (true) {
            try {
                System.out.println("Client message: " + input.readUTF());
            } catch (IOException e) {
                System.out.println("Connection has been closed");
                break;
            }
        }
        }).start();

    }
    private static void writeMessage (DataOutputStream output) {
        Scanner inputText = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    output.writeUTF(inputText.nextLine());
                } catch (IOException e) {
                    System.out.println("Connection has been closed");
                    break;
                }
            }
        }).start();

    }
}
