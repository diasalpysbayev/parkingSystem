package gui.addInformation;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import animation.Shake;
import gui.base.OutStream;
import gui.base.StaticVariable;
import gui.base.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.ServerRunnable;
public class AddCarController extends NewScene {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NameField;

    @FXML
    private TextField SurnameField;

    @FXML
    private TextField PhoneNumberField;

    @FXML
    private CheckBox MaleBox;

    @FXML
    private CheckBox FemaleBox;

    @FXML
    private Button RegisterButton;

    @FXML
    public TextField CarIdField;

    private OutStream out = new OutStream();


@FXML
    void initialize() {
        fillTextField();
        RegisterButton.setOnAction(event -> {
            try {
                if((NameField.getText().equals("") || SurnameField.getText().equals("") || PhoneNumberField.getText().equals("") || CarIdField.getText().equals(""))) {
                    Shake shake1 = new Shake(NameField);
                    Shake shake2 = new Shake(SurnameField);
                    Shake shake3 = new Shake(PhoneNumberField);
                    Shake shake4 = new Shake(CarIdField);
                    Shake shake5 = new Shake(MaleBox);
                    Shake shake6 = new Shake(FemaleBox);
                    shake1.play();
                    shake2.play();
                    shake3.play();
                    shake4.play();
                    shake5.play();
                    shake6.play();
                }
                else{
                    String nameOfUser = NameField.getText().trim();
                    String surnameOfUser = SurnameField.getText().trim();
                    String phoneNumber = PhoneNumberField.getText().trim();
                    String gender;
                    String id_car = CarIdField.getText();
                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date now = new Date();
                    String strDate = sdfDate.format(now);

                    if (MaleBox.isSelected())
                        gender = "Male";
                    else
                        gender = "Female";

                    String check;
                    if (StaticVariable.check == true)
                        check = "yes";
                    else
                        check = "no";

                    out.writeRequest("ADD_USER", new User(1, nameOfUser, surnameOfUser, phoneNumber, gender, id_car, strDate, check));

                    NameField.clear();
                    SurnameField.clear();
                    PhoneNumberField.clear();
                    CarIdField.clear();
                    newScene("VIP", "CreditCard.fxml");
                    RegisterButton.getScene().getWindow().hide();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public void fillTextField(){
        try {
            CarIdField.setText(Integer.toString(ServerRunnable.getIdCar() + 1));
       }catch (Exception e){
        e.printStackTrace();
    }
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