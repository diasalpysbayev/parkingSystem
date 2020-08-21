package gui.addInformation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import animation.Shake;
import gui.base.CreditCardInfo;
import gui.base.OutStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreditCardController implements Initializable {
ObservableList month = FXCollections.observableArrayList();
ObservableList year = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CardNumberField;

    @FXML
    private ChoiceBox<String> MonthBox;

    @FXML
    private ChoiceBox<String> YearBox;

    @FXML
    private PasswordField CvvField;

    @FXML
    private TextField NameField;

    @FXML
    private Button UpdateButton;
    OutStream out = new OutStream();

    @FXML
    void initialize() {
        UpdateButton.setOnAction(event -> {
            try {
                if(CvvField.getText().equals("") || NameField.getText().equals("") || CardNumberField.getText().equals("")) {
                    Shake shake1 = new Shake(CvvField);
                    Shake shake2 = new Shake(NameField);
                    Shake shake3 = new Shake(CardNumberField);
                    shake1.play();
                    shake2.play();
                    shake3.play();
                }
                else {
                    String cardNumber = CardNumberField.getText().trim();
                    String cardName = NameField.getText();
                    out.writeRequestCreditCard("CREDIT", new CreditCardInfo(cardNumber, MonthBox.getValue(), YearBox.getValue(), cardName));
                    CardNumberField.clear();
                    NameField.clear();
                    CvvField.clear();
                    MonthBox.setValue("01");
                    YearBox.setValue("2020");
                    UpdateButton.getScene().getWindow().hide();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        year.removeAll(year);
        year.addAll("2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033");
        YearBox.getItems().addAll(year);
        month.removeAll(month);
        month.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        MonthBox.getItems().addAll(month);
        MonthBox.setValue("01");
        YearBox.setValue("2020");
        initialize();
    }
}
