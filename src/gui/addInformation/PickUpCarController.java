package gui.addInformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animation.Shake;
import gui.base.OutStream;
import gui.base.Paying;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PickUpCarController extends NewScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CarIdField;

    @FXML
    private TextArea AnnouncementText;

    @FXML
    private Button PayButton;
    private OutStream out = new OutStream();

    @FXML
    void initialize() {

        PayButton.setOnAction(event -> {

            try {
                if(CarIdField.getText().equals("")){
                    Shake shake1 = new Shake(CarIdField);
                    shake1.play();
                }
                else {
                    String carId = CarIdField.getText().trim();
                    out.writeRequestDate("DATE", new Paying(null, Byte.parseByte(carId)));
                    CarIdField.clear();
                    PayButton.getScene().getWindow().hide();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
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
