package gui.vip;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import animation.Shake;
import gui.base.OutStream;
import gui.addInformation.NewScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class VipController extends NewScene {
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField EmailField;

        @FXML
        private Button PayButton;
        @FXML
        void initialize() {
            PayButton.setOnAction(event -> {

                try {
                    if(EmailField.getText().equals("")){
                        Shake shake1 = new Shake(EmailField);
                        shake1.play();
                    }
                    else {
                        String to = EmailField.getText();
                        String from = "alpysbayevdias01@gmail.com";

                        final Properties properties = new Properties();
                        properties.load(getClass().getClassLoader().getResourceAsStream("gui/mail.properties"));
                        Session session = Session.getDefaultInstance(properties);

                        MimeMessage message = new MimeMessage(session);

                        message.setFrom(new InternetAddress(from));

                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                        message.setSubject("Test Mail from Java Program");

                        message.setText("You have successfully bought the prime account for a month" + "."
                                + "best regards, Parking Administrator");

                        Transport tr = session.getTransport();
                        tr.connect(null, "Hosedias01");
                        tr.sendMessage(message, message.getAllRecipients());
                        tr.close();
                        System.out.println("Email Sent successfully....");
                        EmailField.clear();
                        newScene("Add Car", "../addInformation/CarInformation.fxml");
                        EmailField.clear();
                        PayButton.getScene().getWindow().hide();
                    }
                } catch (MessagingException mex) {
                    mex.printStackTrace();
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
