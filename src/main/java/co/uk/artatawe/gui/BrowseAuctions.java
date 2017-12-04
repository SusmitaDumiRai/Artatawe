package co.uk.artatawe.gui;

import co.uk.artatawe.sample.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Class to test browsing auction.
 * Probably redundant
 *
 * @author 908928
 */
public class BrowseAuctions extends Application {

      @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BrowseAuctions.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Login Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final ImageView imv = new ImageView();
        final Image image2 = new Image(Main.class.getResourceAsStream("..//artworkpictures//Demeter 1.png"));
        imv.setImage(image2);
    }


    public static void main(String[] args) {
        launch(args);

    }

}
