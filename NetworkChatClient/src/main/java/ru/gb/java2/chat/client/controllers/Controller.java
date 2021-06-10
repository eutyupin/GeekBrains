package ru.gb.java2.chat.client.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.gb.java2.chat.client.MainChat;
import ru.gb.java2.chat.client.Network;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Controller {
    private static final String USER_LIST_PREFFIX = "/ul";
    private static final String USER_PRIVATE_MESSAGE = "/w";
    public static final String TO_ALL_USERS = "Все пользователи";
    private String privateUser;
    @FXML
    private Label currentUserLabel;
    @FXML
    public ListView userList;
    @FXML
    private TextArea chatField;
    @FXML
    private TextArea messageField;
    @FXML
    private Button sendButton;
    private Network network;
    private MainChat application;

    @FXML
    private void sendMessage(ActionEvent actionEvent) {
        sendMessageAction();
    }

    private void sendMessageAction() {
        String message = messageField.getText().trim();
        if (message.isEmpty()) {
            messageField.clear();
            return;
        }
        String sender = application.getCurrentUserName();
        String receiver;
        if (!userList.getSelectionModel().isEmpty()) {
            receiver = userList.getSelectionModel().getSelectedItem().toString();
        } else {receiver = currentUserLabel.getText();}
        if(receiver.equals(TO_ALL_USERS)) {
            message = String.join(",",sender,receiver, message);
        } else {
            message = String.join(",", USER_PRIVATE_MESSAGE,sender, receiver, message);
        }
        try {
            network.sendMessage(message);
        } catch (IOException e) {
            application.showNetworkErrorDialog("Ошибка передачи данных по сети", "Не удалось отправить сообщение!");
        }
        chatFieldAddMessage("Я -> " + receiver, messageField.getText().trim());

    }

    private void chatFieldAddMessage(String sender, String message) {
        chatField.appendText(DateFormat.getDateTimeInstance().format(new Date()));
        chatField.appendText(System.lineSeparator());
        if (sender != null) {
            chatField.appendText(sender + ":");
            chatField.appendText(System.lineSeparator());
        }
        chatField.appendText(message);
        chatField.appendText(System.lineSeparator());
        chatField.appendText(System.lineSeparator());
        messageField.clear();
    }

    @FXML
    private void choiceUser(MouseEvent mouseEvent) {
        currentUserLabel.setText(userList.getSelectionModel().getSelectedItem().toString());
        privateUser = userList.getSelectionModel().getSelectedItem().toString();
    }

    public void messageFieldKeyPressed(KeyEvent keyEvent) {
        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                if (event.isShiftDown() || event.isControlDown() || event.isAltDown()) {
                    messageField.appendText(System.lineSeparator());
                } else {
                    if(!messageField.getText().isEmpty()){
                        sendMessageAction();
                    }
                }
            }
        });
    }
    public void setNetwork(Network network) {
        this.network = network;
        network.waitMessages(message -> Platform.runLater(() -> {
            if(message.startsWith(USER_LIST_PREFFIX)) {
                userListUpdate(message);
            } else if(message.startsWith(USER_PRIVATE_MESSAGE)) {
                privateMessageProcess(message);
            } else {
                allUsersMessage(message);
            }
        }));
    }

    private void allUsersMessage(String message) {
        String[] tempMessage = message.split(",");
        chatFieldAddMessage(tempMessage[0] + " -> " + tempMessage[1], tempMessage[2]);
    }

    private void privateMessageProcess(String message) {
        String[] tempMessage = message.split(",");
        chatFieldAddMessage(tempMessage[1] + " -> мне лично", tempMessage[2]);
    }

    private void userListUpdate(String message) {
        String[] tempUserList = message.split(",");
        userList.getItems().clear();
        for (int i = 1; i < tempUserList.length; i++) {
            if (!tempUserList[i].equals(application.getCurrentUserName())) {
                userList.getItems().add(tempUserList[i]);
            }
        }
    }

    public void setApplication(MainChat application) {
        this.application = application;
    }

}

