package ru.geekbrains.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClientMain {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8189;
    private static final String NETWORK_ERROR_CONNECTION_TYPE = "Невозможно установить сетевое соединение";
    private static Socket socket;
    private static DataInputStream socketInput;
    private static DataOutputStream socketOutput;

    public static void main(String[] args) {
        connectToServer();
        readMessage(socketInput);
        writeMessage(socketOutput);

    }
    private static void connectToServer() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            socketInput = new DataInputStream(socket.getInputStream());
            socketOutput = new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException e) {
            String errMsg = "хост неизвестен!";
            System.err.println(errMsg);
            System.out.println(NETWORK_ERROR_CONNECTION_TYPE + " "+ errMsg);
            e.printStackTrace();
        } catch (IOException e) {
            String errMsg = "не удалось установить соединение с сервером!";
            System.err.println(errMsg);
            System.out.println(NETWORK_ERROR_CONNECTION_TYPE + " "+ errMsg);
            e.printStackTrace();
        }
    }

    private static void readMessage(DataInputStream input) {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Server message: " + input.readUTF());
                } catch (IOException e) {
                    System.out.println("Connection has been closed");
                    break;
                }
            }
        }).start();

    }

    private static void writeMessage(DataOutputStream output) {
        Scanner inputText = new Scanner(System.in);
        new Thread(() -> {
            while (true) {
                try {
                    output.writeUTF(inputText.nextLine());
                } catch (IOException e) {
                    System.out.println("Connection has been closed");
//                    break;
                }
            }
        }).start();
    }

}
