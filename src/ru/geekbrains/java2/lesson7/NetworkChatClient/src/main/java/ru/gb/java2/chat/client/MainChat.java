package ru.geekbrains.java2.lesson7.NetworkChatClient.src.main.java.ru.gb.java2.chat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.geekbrains.java2.lesson7.NetworkChatClient.src.main.java.ru.gb.java2.chat.client.controllers.AuthController;
import ru.geekbrains.java2.lesson7.NetworkChatClient.src.main.java.ru.gb.java2.chat.client.controllers.Controller;

public class MainChat extends Application {

    private static final String NETWORK_ERROR_TITLE = "Сетевая ошибка";
    private static final String AUTH_ERROR_TITLE = "Аутентификация";
    private static final String NETWORK_ERROR_CONNECTION_TYPE = "Невозможно установить сетевое соединение";
    private Stage primaryStage;
    private Network network;
    private Stage authStage;
    private String currentUserName;
    private boolean authorizedUser = false;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("NetChat");
        primaryStage.setScene(new Scene(root, 500  , 500));
        primaryStage.show();
        Controller controller = fxmlLoader.getController();
        connectToServer(controller);

        FXMLLoader authLoader = new FXMLLoader();
        authLoader.setLocation(MainChat.class.getResource("authDialog.fxml"));
        AnchorPane authDialogPanel = authLoader.load();

        authStage = new Stage();
        authStage.initOwner(primaryStage);
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.setScene(new Scene(authDialogPanel));
        AuthController authController = authLoader.getController();
        authController.setMainChat(this);
        authController.setNetwork(network);
        autoCloseAplicationWhenCancell();
        authStage.showAndWait();
        controller.setNetwork(network);
    }

    private void autoCloseAplicationWhenCancell() {
        authStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(() -> {
                    if (!authorizedUser) {
                        primaryStage.close();
                    }
                });
            }
        });
    }

    public Stage getAuthStage() {
        return authStage;
    }

    private void connectToServer(Controller controller) {
        network = new Network();
        boolean result = network.connect();
        if (!result) {
            String errMsg = "Не удалось установить соединение с сервером!";
            showNetworkErrorDialog(NETWORK_ERROR_CONNECTION_TYPE, errMsg);
            return;
        }

        controller.setApplication(this);
        primaryStage.setOnCloseRequest(windowEvent -> network.close());
    }

    private void showErrorDialog(String title, String type, String details) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(type);
        alert.setContentText(details);
        alert.showAndWait();
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
        primaryStage.setTitle("NetChat: " + currentUserName);
    }

    public void showNetworkErrorDialog(String type, String details) {
        showErrorDialog(NETWORK_ERROR_TITLE, type, details);
    }
    public void showAuthErrorDialog(String type, String details) {
        showErrorDialog(AUTH_ERROR_TITLE, type, details);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean isAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(boolean authorizedUser) {
        this.authorizedUser = authorizedUser;
    }
}
