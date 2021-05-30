package ru.geekbrains.java2.lesson4.NetChat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;

import java.util.ArrayList;

public class Controller {
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

    @FXML
    private void sendMessage(ActionEvent actionEvent) {
        movementsForSend();
    }
    private void movementsForSend() {
        chatField.appendText(messageField.getText() + System.lineSeparator());
        messageField.clear();
    }

    @FXML
    private void choiceUser(MouseEvent mouseEvent) {
        currentUserLabel.setText(userList.getSelectionModel().getSelectedItem().toString());
    }

    public void userListAdd(ArrayList<String> users) {
       userList.getItems().addAll(users);
    }

    public void chatFieldKeyPressed(KeyEvent keyEvent) {

    }

    public void messageFieldKeyPressed(KeyEvent keyEvent) {
        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                if (event.isShiftDown() || event.isControlDown() || event.isAltDown()) {
                    messageField.appendText(System.lineSeparator());
                } else {
                    if(!messageField.getText().isEmpty()){
                        movementsForSend();
                    }
                }
            }
        });
    }
}
