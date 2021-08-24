package ru.gb.java2.chat.client.controllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.gb.java2.chat.client.MainChat;
import ru.gb.java2.chat.client.Network;
import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.*;

import java.io.IOException;

public class ChangeUserNameDialog {

    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField userNameField;
    private MainChat mainChat;
    private Network network;

    public void okAction(ActionEvent actionEvent) {
        try {
            network.sendChangeUsernameCommand(mainChat.getCurrentUserName(),userNameField.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNetwork(Network network) {
        this.network = network;
        network.waitMessages(command -> Platform.runLater(() -> {
            serverCommandCheck(command);
        }));
    }

    public void cancelAction(ActionEvent actionEvent) {
        mainChat.getChangeUserNameDialog().close();
    }

    public void setMainChat(MainChat mainChat) {
        this.mainChat = mainChat;
    }

    private void serverCommandCheck(Command command) {
        if(command.getType() == CommandType.CHANGE_USER_NAME_STATUS) {
            ChangeUserNameStatusCommand data = (ChangeUserNameStatusCommand) command.getData();
            Thread.currentThread().interrupt();
            Platform.runLater(() -> {
                showStatusDialog(data.getFlag(), data.getMessage());
                mainChat.getChangeUserNameDialog().close();
            });
        }
    }

    private void showStatusDialog(Flag flag, String message) {
        if (flag == Flag.OK) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Статус изменения имени пользователя");
            alert.setHeaderText("Все ОК!");
            alert.setContentText(message);
            alert.showAndWait();
        } else if(flag == Flag.ERROR) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Статус изменения имени пользователя");
            alert.setHeaderText("Ошибка!");
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

}
