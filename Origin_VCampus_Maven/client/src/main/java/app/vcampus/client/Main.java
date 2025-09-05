package app.vcampus.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setResizable(false);
        showLogin();
    }

    public static void showLogin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/app/vcampus/client/scene/LoginScene.fxml")));
        primaryStage.setTitle("VCampus Login");
        primaryStage.setScene(new Scene(root, 1064, 600));
        primaryStage.show();
    }

    public static void showMainPanel() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/app/vcampus/client/scene/MainPanel.fxml")));
        primaryStage.setTitle("VCampus");
        primaryStage.setScene(new Scene(root, 1400, 800));
        primaryStage.setResizable(true);
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}