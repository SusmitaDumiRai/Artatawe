package co.uk.artatawe.sample;

import co.uk.artatawe.controller.BrowseAuctionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Plamena on 3.12.2017 г..
 */
public class NavigationTest extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        
    	Parent root = FXMLLoader.load(getClass().getResource("../gui/Navigation.fxml"));
    
    	
        Scene scene = new Scene(root);



        primaryStage.setTitle("Navigation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
