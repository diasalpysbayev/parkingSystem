package gui.vip;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animation.Shake;
import gui.base.OutStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VipUseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ContinueButton;

    @FXML
    private TextField CarIdField;
    OutStream out = new OutStream();

    @FXML
    void initialize() {
        ContinueButton.setOnAction(event ->{
            try {
                if(CarIdField.getText().equals("")){
                    Shake shake1 = new Shake(CarIdField);
                    shake1.play();
                }
                else {
                    String carId = CarIdField.getText();
                    out.writeRequestVip("ADD_VIP", carId);
                    CarIdField.clear();
                    ContinueButton.getScene().getWindow().hide();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
