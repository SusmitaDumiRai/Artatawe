package co.uk.artatawe.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Plamena on 3.12.2017 г..
 */
public class LoginTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));

        Scene scene = new Scene(root);



        primaryStage.setTitle("Login Test");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);

    }
}
