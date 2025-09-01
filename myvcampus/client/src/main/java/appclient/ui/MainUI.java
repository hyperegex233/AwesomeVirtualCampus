package appclient.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainUI {

    public void show(String username) {
        Stage stage = new Stage();
        stage.setTitle("Campus Information System");

        // 顶部标题栏
        Label title = new Label("校园信息管理系统");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");
        HBox header = new HBox(title);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-background-color: #4CAF50;"); // 绿色标题栏

        // 左侧导航栏
        VBox nav = new VBox(10);
        nav.setPadding(new Insets(15));
        nav.setStyle("-fx-background-color: #f5f5f5;");
        nav.setPrefWidth(150);

        Button userBtn = createNavButton("用户管理");
        Button courseBtn = createNavButton("课程管理");
        Button bookBtn = createNavButton("图书管理");
        Button logoutBtn = createNavButton("退出");

        nav.getChildren().addAll(userBtn, courseBtn, bookBtn, logoutBtn);

        // 内容区（初始为欢迎页）
        Label content = new Label("欢迎回来, " + username + "!");
        content.setStyle("-fx-font-size: 16px; -fx-text-fill: #333;");
        StackPane contentPane = new StackPane(content);
        contentPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 12; -fx-border-radius: 12;");
        contentPane.setPadding(new Insets(20));

        // 点击按钮切换内容
        userBtn.setOnAction(e -> content.setText("这里是用户管理页面"));
        courseBtn.setOnAction(e -> content.setText("这里是课程管理页面"));
        bookBtn.setOnAction(e -> content.setText("这里是图书管理页面"));
        logoutBtn.setOnAction(e -> stage.close());

        // 布局
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setLeft(nav);
        root.setCenter(contentPane);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    // 美观按钮样式
    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(120);
        btn.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #ddd;" +
                        "-fx-background-radius: 8; -fx-border-radius: 8;" +
                        "-fx-padding: 8; -fx-font-size: 14px;"
        );
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: #e8f5e9; -fx-border-color: #4CAF50;" +
                        "-fx-background-radius: 8; -fx-border-radius: 8;" +
                        "-fx-padding: 8; -fx-font-size: 14px;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #ddd;" +
                        "-fx-background-radius: 8; -fx-border-radius: 8;" +
                        "-fx-padding: 8; -fx-font-size: 14px;"
        ));
        return btn;
    }
}
