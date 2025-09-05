package app.vcampus.client.scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private NavRailController navRailController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Pass this controller to the navRail controller
        navRailController.setMainPanelController(this);

        // Load home view by default
        loadView("/app/vcampus/client/scene/subscene/HomeView.fxml");
    }

    public void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            root.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}