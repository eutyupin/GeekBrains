package ru.geekbrains.java2.lesson4.NetChat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainChat extends Application {
    private ArrayList<String> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("NetChat");
        primaryStage.setScene(new Scene(root, 500  , 500));
        primaryStage.show();
        Controller controller = fxmlLoader.getController();

        for(int i = 0; i < 25; i++) users.add("User " + (i+1));
        controller.userListAdd(users);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
