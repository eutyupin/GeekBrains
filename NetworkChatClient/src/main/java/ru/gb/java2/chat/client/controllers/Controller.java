package ru.gb.java2.chat.client.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.gb.java2.chat.client.MainChat;
import ru.gb.java2.chat.client.Network;
import ru.gb.java2.chat.clientserver.Command;
import ru.gb.java2.chat.clientserver.commands.ClientMessageCommandData;
import ru.gb.java2.chat.clientserver.commands.CommandType;
import ru.gb.java2.chat.clientserver.commands.Flag;
import ru.gb.java2.chat.clientserver.commands.UpdateUserListCommandData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private ChangeUserNameDialog changeUserNameDialog;
    @FXML
    private void sendMessage(ActionEvent actionEvent) {
        try {
            sendMessageAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainChat(MainChat mainChat) {
        this.mainChat = mainChat;
    }

    private void sendMessageAction() throws IOException {
        String message = messageField.getText().trim();
        if (message.isEmpty()) {
            messageField.clear();
            return;
        }
        String receiver = TO_ALL_USERS;
        if (!userList.getSelectionModel().isEmpty()) {
            receiver = userList.getSelectionModel().getSelectedItem().toString();
        }
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

    private void chatFieldAddMessage(String sender, String message) throws IOException {
        chatField.appendText(DateFormat.getDateTimeInstance().format(new Date()));
        appendHistoryToFile(DateFormat.getDateTimeInstance().format(new Date()));
        chatField.appendText(System.lineSeparator());
        appendHistoryToFile(System.lineSeparator());
        if (sender != null) {
            chatField.appendText(sender + ":");
            appendHistoryToFile(sender + ":");
            chatField.appendText(System.lineSeparator());
            appendHistoryToFile(System.lineSeparator());
        }
        chatField.appendText(message);
        appendHistoryToFile(message);
        chatField.appendText(System.lineSeparator());
        appendHistoryToFile(System.lineSeparator());
        chatField.appendText(System.lineSeparator());
        appendHistoryToFile(System.lineSeparator());
        messageField.clear();
        mainChat.writeData.flush();
    }

    @FXML
    private void choiceUser(MouseEvent mouseEvent) {
        try {
            privateUser = userList.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void messageFieldKeyPressed(KeyEvent keyEvent) {
        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                if (event.isShiftDown() || event.isControlDown() || event.isAltDown()) {
                    messageField.appendText(System.lineSeparator());
                } else {
                    if(!messageField.getText().isEmpty()){
                        try {
                            sendMessageAction();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    public void setNetwork(Network network){
        this.network = network;
        network.waitMessages(command -> Platform.runLater(() -> {
            try {
                serverCommandCheck(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    private void serverCommandCheck(Command command) throws IOException {
        if (command.getType() == CommandType.CLIENT_MESSAGE) {
            ClientMessageCommandData data = (ClientMessageCommandData) command.getData();
            if (data.getFlag() == Flag.PRIVATE) privateMessageProcess(data.getSender(), data.getMessage());
            else if (data.getFlag() == Flag.ALL) allUsersMessage(data.getSender(), data.getMessage());
        } else if (command.getType() == CommandType.UPDATE_USER_LIST) {
            UpdateUserListCommandData data = (UpdateUserListCommandData) command.getData();
            userListUpdate(data.getUsers());
        }
    }

    private void allUsersMessage(String sender, String message) throws IOException {
        chatFieldAddMessage(sender + " -> Все пользователи", message);
    }

    private void privateMessageProcess(String sender, String message) throws IOException {
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

    @FXML
    public void ReClick(ActionEvent actionEvent) throws IOException {
        network.close();
        mainChat.globalConnect();
    }

    @FXML
    public void changeNameAction(ActionEvent actionEvent) {
        mainChat.changeNameSetNetwork();
        mainChat.getChangeUserNameDialog().showAndWait();
    }

    /* формируем текст чата из эррэй листа считанного из файла */

    public void addHistoryToChat(ArrayList<String> messageList) {
        for (String line: messageList) {
          if(messageList.size() > 0) chatField.appendText(line + System.lineSeparator());
        }
    }

    /* дописываем строки в файл при добавлении в чат */

    private void appendHistoryToFile(String messageLine) {
        try {
           mainChat.writeData.append(messageLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

