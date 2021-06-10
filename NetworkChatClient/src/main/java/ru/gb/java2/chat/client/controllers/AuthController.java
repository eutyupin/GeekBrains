package ru.gb.java2.chat.client.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.gb.java2.chat.client.MainChat;
import ru.gb.java2.chat.client.Network;

import java.io.IOException;
import java.util.function.Consumer;

public class AuthController {
    private static final String AUTH_OK_COMMAND = "/authOK";
    private static final String AUTH_COMMAND = "/auth";
    private static final String INVALID_CREDENTIALS = "Некоректный ввод данных";
    private static final String CREDENTIAL_REQUIRED = "Логин и пароль должны быть указаны!";
    public static final String USER_ERROR_COMMAND = "Некорректные логин или пароль";
    private static final String USER_ALREADY_EXIST = "Пользователь с таким именем уже авторизован";
    private MainChat mainChat;
    private Network network;

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button authButton;

    @FXML
    public void executeAuth(ActionEvent actionEvent) {
        actionsForLogin();
    }

    private void actionsForLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            mainChat.showAuthErrorDialog(INVALID_CREDENTIALS, CREDENTIAL_REQUIRED);
            return;
        }
        String authCommandMessage = String.join(",", AUTH_COMMAND, login, password);
        try {
            network.sendMessage(authCommandMessage);
        } catch (IOException e) {
            mainChat.showNetworkErrorDialog("Ошибка передачи данных по сети", "Не удалось отправить сообщение!");
            e.printStackTrace();
        }
    }

    public void setMainChat(MainChat mainChat) {
        this.mainChat = mainChat;
    }

    public void setNetwork(Network network) {
        this.network = network;
        network.waitMessages(new Consumer<String>() {
            @Override
            public void accept(String message) {
                if(message.startsWith(AUTH_OK_COMMAND)) {
                    Thread.currentThread().interrupt();
                    Platform.runLater(() -> {
                        mainChat.getAuthStage().close();
                        mainChat.setCurrentUserName(message.substring(8));

                    });
                } else if (message.equals(USER_ERROR_COMMAND)){
                    Platform.runLater(() -> {
                    mainChat.showAuthErrorDialog(INVALID_CREDENTIALS, "Пользователя с таким логином и паролем не существует!");
                    loginField.clear();
                    passwordField.clear();
                    loginField.requestFocus();
                    });
                } else if (message.equals(USER_ALREADY_EXIST)) {
                    Platform.runLater(() -> {
                        mainChat.showAuthErrorDialog(INVALID_CREDENTIALS, USER_ALREADY_EXIST);
                        loginField.clear();
                        passwordField.clear();
                        loginField.requestFocus();
                    });
                }
            }
        });
    }

    public void loginKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) actionsForLogin();
    }

    public void passwordKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) actionsForLogin();
    }
}
