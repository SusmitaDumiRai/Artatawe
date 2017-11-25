package sample;

import Database.DatabaseManager;
import Database.UserDatabaseManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    //    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
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
        userDatabaseManager.createUserTable();
       // userDatabaseManager.insertIntoTable();
        userDatabaseManager.selectAll();
    }
}