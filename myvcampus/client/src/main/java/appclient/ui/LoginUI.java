package appclient.ui;

import appclient.model.UserModel;
import appclient.net.Client;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginUI extends Application {

    private final Client client = new Client("localhost", 8000);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Campus System - Login");

        // GridPane 布局
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(10);

        Label title = new Label("Login");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");
        GridPane.setColumnSpan(title, 2);

        Label userLabel = new Label("Username:");
        TextField userInput = new TextField();
        userInput.setPromptText("Enter username");
        userInput.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-padding: 6;");

        Label passLabel = new Label("Password:");
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Enter password");
        passInput.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-padding: 6;");

        Button loginButton = new Button("Login");
        loginButton.setDefaultButton(true);
        loginButton.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;" +
                        "-fx-background-radius: 8; -fx-padding: 8 16;"
        );

        loginButton.setOnMouseEntered(e -> loginButton.setStyle(
                "-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-weight: bold;" +
                        "-fx-background-radius: 8; -fx-padding: 8 16;"
        ));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(
                "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;" +
                        "-fx-background-radius: 8; -fx-padding: 8 16;"
        ));

        // 登录逻辑
        loginButton.setOnAction(e -> {
            String username = userInput.getText();
            String password = passInput.getText();

            Task<Boolean> task = new Task<>() {
                @Override
                protected Boolean call() {
                    return client.login(new UserModel(username, password));
                }
            };

            task.setOnSucceeded(ev -> {
                boolean success = task.getValue();
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Success!");
                    alert.showAndWait();

                    // 打开美观主界面
                    new MainUI().show(username);

                    primaryStage.close();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Login Failed!");
                    alert.showAndWait();
                }
            });

            new Thread(task).start();
        });

        // 回车触发登录
        passInput.setOnAction(e -> loginButton.fire());

        // 把组件放进 GridPane
        grid.add(title, 0, 0, 2, 1);
        grid.add(userLabel, 0, 1);
        grid.add(userInput, 1, 1);
        grid.add(passLabel, 0, 2);
        grid.add(passInput, 1, 2);
        grid.add(loginButton, 1, 3);

        // 背景样式
        VBox root = new VBox(grid);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f0f4f7, #d9e2ec);");

        Scene scene = new Scene(root, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
