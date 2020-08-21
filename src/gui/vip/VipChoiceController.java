package gui.vip;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.addInformation.NewScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VipChoiceController extends NewScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button UsePrimeButton;

    @FXML
    private Button BuyPrimeButton;

    @FXML
    void initialize() {
        UsePrimeButton.setOnAction(event -> {
            newScene("VIP", "../vip/VipUse.fxml");
            UsePrimeButton.getScene().getWindow().hide();
        });

        BuyPrimeButton.setOnAction(event -> {
            newScene("VIP", "../vip/Vip.fxml");
            BuyPrimeButton.getScene().getWindow().hide();
        });
    }

    public void newScene(String title, String directory) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(directory));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.showAndWait();
    }
}
