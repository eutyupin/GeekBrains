package ru.gb.java2.chat.client.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import ru.gb.java2.chat.clientserver.commands.ClientMessageCommandData;
import ru.gb.java2.chat.clientserver.commands.CommandType;
import ru.gb.java2.chat.clientserver.commands.Flag;
import ru.gb.java2.chat.clientserver.commands.UpdateUserListCommandData;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private final String TO_ALL_USERS = "Все пользователи";
    private String privateUser;
    private MainChat mainChat;
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

    public void setMainChat(MainChat mainChat) {
        this.mainChat = mainChat;
    }

    private void sendMessageAction() {
        String message = messageField.getText().trim();
        if (message.isEmpty()) {
            messageField.clear();
            return;
        }
        String receiver;
        if (!userList.getSelectionModel().isEmpty()) {
            receiver = userList.getSelectionModel().getSelectedItem().toString();
        } else {receiver = currentUserLabel.getText();}
        if(receiver.equals(TO_ALL_USERS)) {
            try {
                network.sendMessage(message);
            } catch (IOException e) {
                application.showNetworkErrorDialog("Ошибка передачи данных по сети", "Не удалось отправить сообщение!");
            }
        } else {
            try {
                network.sendPrivateMessage(receiver,message);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public void setNetwork(Network network){
        this.network = network;
        network.waitMessages(command -> Platform.runLater(() -> {
            if (command.getType() == CommandType.CLIENT_MESSAGE) {
                ClientMessageCommandData data = (ClientMessageCommandData) command.getData();
                if (data.getFlag() == Flag.PRIVATE) privateMessageProcess(data.getSender(), data.getMessage());
                else if (data.getFlag() == Flag.ALL) allUsersMessage(data.getSender(), data.getMessage());
            } else if (command.getType() == CommandType.UPDATE_USER_LIST) {
                UpdateUserListCommandData data = (UpdateUserListCommandData) command.getData();
                userListUpdate(data.getUsers());
            }
        }));
    }

    private void allUsersMessage(String sender, String message) {
        chatFieldAddMessage(sender + " -> Все пользователи", message);
    }

    private void privateMessageProcess(String sender, String message) {
        chatFieldAddMessage(sender + " -> Мне лично", message);
    }

    public synchronized void userListUpdate(List<String> users) {
        ArrayList<String> newList = new ArrayList<>();
        for (String user : users) {
            if (!user.equals(mainChat.getCurrentUserName())) {
                newList.add(user);
            }
        }
        newList.add(0,TO_ALL_USERS);
        userList.setItems(FXCollections.observableArrayList(newList));
    }

    public void setApplication(MainChat application) {
        this.application = application;
    }

    public void reClick(ActionEvent actionEvent) throws IOException {}

    public void ReClick(ActionEvent actionEvent) throws IOException {
        network.close();
        mainChat.globalConnect();
    }
}

