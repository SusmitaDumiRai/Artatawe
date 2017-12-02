package co.uk.artatawe.sample;

import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.DatabaseManager;
import co.uk.artatawe.database.UserDatabaseManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //    Parent root = FXMLLoader.load(getClass().getResource("co.uk.artatawe.sample.fxml"));
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();
    }

/*
    public static void main(String[] args) {
        launch(args);

    }
    */

    public static void main(String[] args) {
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        //creates table if not exist.
        //  userDatabaseManager.createUserTable();
        artworkDatabaseManager.createArtworkTable();


        artworkDatabaseManager.getAllArtworks();

        System.out.println("Displaying all users");


           userDatabaseManager.getAllUsers();

    }
}
;