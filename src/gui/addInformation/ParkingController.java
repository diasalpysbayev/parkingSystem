package gui.addInformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.base.OutStream;
import gui.base.StaticVariable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ParkingController extends NewScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ParkingCarButton;

    @FXML
    private Button PickUpButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button VipButton;

    private OutStream out = new OutStream();

    @FXML
    void initialize() {
        ParkingCarButton.setOnAction(event -> {
            StaticVariable.check = false;
            newScene("Parking car", "CarInformation.fxml");
        });

        PickUpButton.setOnAction(event -> {
            newScene("Pick Up", "PickUpCar.fxml");
        });

        VipButton.setOnAction(event ->{
            StaticVariable.check = true;
            newScene("Parking car", "../vip/VipChoice.fxml");
        });

        ExitButton.setOnAction(event -> {

            try{
                out.writeRequest("DISCONNECT", null);

            }catch (Exception e){
                e.printStackTrace();
            }

            System.exit(0);
        });
    }

    @Override
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
