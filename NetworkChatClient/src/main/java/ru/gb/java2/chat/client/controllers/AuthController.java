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
import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.AuthOKCommandData;
import ru.gb.java2.chat.clientserver.commands.CommandType;
import ru.gb.java2.chat.clientserver.commands.ErrorCommandData;
import java.io.IOException;

public class AuthController {
    private static final String INVALID_CREDENTIALS = "Некоректный ввод данных";
    private static final String CREDENTIAL_REQUIRED = "Логин и пароль должны быть указаны!";
    private Network network;
    private MainChat mainChat;

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

    public void setMainChat(MainChat mainChat) {
        this.mainChat = mainChat;
    }

    private void actionsForLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            mainChat.showAuthErrorDialog(INVALID_CREDENTIALS, CREDENTIAL_REQUIRED);
            return;
        }
        try {
            network.sendAuthCommand(login, password);
        } catch (IOException e) {
            mainChat.showNetworkErrorDialog("Ошибка передачи данных по сети", "Не удалось отправить сообщение!");
            e.printStackTrace();
        }
    }

    public void setNetwork(Network network){
        this.network = network;
        network.waitMessages(command -> Platform.runLater(() -> {
                serverCommandCheck(command);
        }));
    }

    private void serverCommandCheck(Command command) {
        if(command.getType() == CommandType.AUTH_OK) {
            AuthOKCommandData data = (AuthOKCommandData) command.getData();
            Thread.currentThread().interrupt();
            Platform.runLater(() -> {
                mainChat.setAuthorizedUser(true);
                mainChat.getAuthStage().close();
                mainChat.setCurrentUserName(data.getUserName());
            });
        } else if (command.getType() == CommandType.ERROR){
            ErrorCommandData data = (ErrorCommandData) command.getData();
            Platform.runLater(() -> {
                mainChat.showAuthErrorDialog(INVALID_CREDENTIALS, data.getErrorMessage());
                loginField.clear();
                passwordField.clear();
                loginField.requestFocus();
            });
        }
    }

    public void loginKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) actionsForLogin();
    }

    public void passwordKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) actionsForLogin();
    }

}
