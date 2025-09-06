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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;



public class FinanceController implements Initializable {
    @FXML
    private Label balanceLabel;

    @FXML
    private ImageView qrCodeImageView;

    @FXML
    private JFXTextField rechargeAmountField;

    @FXML
    private JFXButton rechargeButton;

    private double balance = 1000.00; // 示例余额

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateBalanceLabel();
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.format("%.2f", balance));
    }

    @FXML
    private void handleRecharge(ActionEvent event) {
        String rechargeRef = "这个链接充值了: " + rechargeAmountField.getText() + "元";
        System.out.println(rechargeRef);
        generateAndDisplayQRCode(rechargeRef);
        //TODO: 真正的请求并等待结果
        balance += Double.parseDouble(rechargeAmountField.getText());
        updateBalanceLabel();
        rechargeAmountField.clear();
    }

    private void generateAndDisplayQRCode(String data) {
        //TODO: 生成二维码并显示在 qrCodeImageView 中
    }
}