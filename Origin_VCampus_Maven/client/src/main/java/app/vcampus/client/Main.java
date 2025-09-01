package app.vcampus.client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Top bar mimicking the original style
        Label titleLabel = new Label("VCampus");
        titleLabel.getStyleClass().add("top-bar-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button closeButton = new Button("X");
        closeButton.getStyleClass().add("close-button");
        closeButton.setOnAction(e -> primaryStage.close());

        HBox topBar = new HBox(titleLabel, spacer, closeButton);
        topBar.getStyleClass().add("top-bar");

        // Make the top bar draggable
        topBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        topBar.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        // Main content area
        Label contentLabel = new Label("JavaFX Content Area");
        contentLabel.getStyleClass().add("page-title-heading");

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(contentLabel);

        Scene scene = new Scene(root, 1400, 800);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("VCampus Client");
        primaryStage.show();
    }
}
