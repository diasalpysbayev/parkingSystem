package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application {
    public static Socket socket;
    static {
        try {
            socket = new Socket("127.0.0.1", 2050);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("addInformation/Parking.fxml"));
        primaryStage.setTitle("Terminal");
        primaryStage.setResizable(false);
        root.setId("pane");
        Scene scene = new Scene(root, 500, 370);
        scene.getStylesheets().addAll(this.getClass().getResource("image/background.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}