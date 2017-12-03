package co.uk.artatawe.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Susi on 03/12/2017.
 */
public class BrowseArtwork  extends Application {

      @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/BrowseArtwork.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Login Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }

}
