package app.vcampus.client.scene;

import app.vcampus.client.viewmodel.LoginViewModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScene implements Initializable {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField passwordTextField;

    @FXML
    private JFXButton togglePasswordButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXTextField serverAddressField;

    private final LoginViewModel viewModel = new LoginViewModel();

    private boolean passwordVisible = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind ViewModel properties to UI controls
        usernameField.textProperty().bindBidirectional(viewModel.username);
        passwordField.textProperty().bindBidirectional(viewModel.password);
        passwordTextField.textProperty().bindBidirectional(viewModel.password);

        // Add listeners to ViewModel state changes
        viewModel.loginState.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Platform.runLater(() -> {
                    try {
                        app.vcampus.client.Main.showMainPanel();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Could not load main application window.");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                });
            }
        });

        viewModel.showMessage.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Show an error message on failed login
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username or password.");
                    alert.showAndWait();
                });
            }
        });

        // Bind the visibility and managed properties of the two password fields
        passwordTextField.visibleProperty().bind(passwordField.managedProperty().not());
        passwordTextField.managedProperty().bind(passwordField.managedProperty().not());
    }

    @FXML
    private void handleLogin() {
        viewModel.login();
    }

    @FXML
    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            passwordField.setManaged(false);
            passwordField.setVisible(false);
            togglePasswordButton.setText("隐藏");
        } else {
            passwordField.setManaged(true);
            passwordField.setVisible(true);
            togglePasswordButton.setText("显示");
        }
    }

    @FXML
    private void handleSetServerAddress() {
        boolean isVisible = serverAddressField.isVisible();
        serverAddressField.setVisible(!isVisible);
        serverAddressField.setManaged(!isVisible);
    }
}
