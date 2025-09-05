package app.vcampus.client.scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {
    // --- 注入 FXML 中的 UI 元素 ---
    /*
    * 注意：这里注入的变量名必须是 `fx:id` 的值后面加上 "Controller"。
    * fx:id 可以在 MainPanel.fxml 里面找
    * */
    @FXML
    private NavRailController navRailController;
    @FXML
    private StackPane contentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Main Panel Initialized");
        navRailController.setMainPanelController(this);
        //loadContent("/app/vcampus/client/scene/sub/HomeView.fxml");
    }

    public void loadContent(String fxmlPath) {
        try {
            // 加载 FXML 文件
            Node view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));

            // 清空现有内容并添加新加载的视图
            contentPane.getChildren().clear();
            contentPane.getChildren().add(view);
            System.out.println("Content loaded: HomeView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            // 实际项目中应显示错误提示
        }
    }
}
