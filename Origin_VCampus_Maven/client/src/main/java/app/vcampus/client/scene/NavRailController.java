package app.vcampus.client.scene;

import javafx.fxml.FXML;

public class NavRailController {
    private MainPanelController mainPanelController;
    public void setMainPanelController(MainPanelController mainPanelController) {
        this.mainPanelController = mainPanelController;
    }

    @FXML
    private void handleHome() {
        System.out.println("Home button clicked");
        if (mainPanelController != null) {
            mainPanelController.loadContent("/app/vcampus/client/scene/sub/HomeView.fxml");
        }
    }

    @FXML
    private void handleStudentStatus() {
        System.out.println("Student Status button clicked");
    }

    @FXML
    private void handleTeachingAffairs() {
        System.out.println("Teaching Affairs button clicked");
    }

    @FXML
    private void handleLibrary() {
        System.out.println("Library button clicked");
    }

    @FXML
    private void handleShop() {
        System.out.println("Shop button clicked");
    }

    @FXML
    private void handleFinance() {
        System.out.println("Finance button clicked");
    }

    @FXML
    private void handleAdmin() {
        System.out.println("Admin button clicked");
    }

    @FXML
    private void handleGpt() {
        System.out.println("GPT button clicked");
    }
}
