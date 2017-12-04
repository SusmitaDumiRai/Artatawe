package co.uk.artatawe.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Testing class to create new auction.
 * @author 908928
 */
public class CreateAuctionTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("co/uk/artatawe/gui/CreateAuction.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Login Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }


}
