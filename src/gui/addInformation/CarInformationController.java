package gui.addInformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animation.Shake;
import gui.base.Car;
import gui.base.OutStream;
import gui.base.Request;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CarInformationController extends NewScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CarModelField;

    @FXML
    private TextField CarColorField;

    @FXML
    private TextField CarNumberField;

    @FXML
    private Button SaveButton;

    private OutStream out = new OutStream();
Request request = new Request();
    @FXML
    void initialize() {
        SaveButton.setOnAction(event -> {
            try {
                if(CarModelField.getText().equals("") || CarColorField.getText().equals("") || CarNumberField.getText().equals("")) {
                    Shake shake1 = new Shake(CarModelField);
                    Shake shake2 = new Shake(CarColorField);
                    Shake shake3 = new Shake(CarNumberField);
                    shake1.play();shake2.play();shake3.play();
                }
                else {
                    String carModel = CarModelField.getText().trim();
                    String carColor = CarColorField.getText().trim();
                    String carNumber = CarNumberField.getText().trim();
                    out.writeRequestCar("ADD_CAR", new Car(1, carModel, carColor, carNumber));

                    CarModelField.clear();
                    CarColorField.clear();
                    CarNumberField.clear();

                    newScene("User", "AddCar.fxml");

                    SaveButton.getScene().getWindow().hide();
                }
            } catch (IOException e) {
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
