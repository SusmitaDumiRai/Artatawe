package co.uk.artatawe.gui;
/**
 * Created by Plamena on 27.11.2017 г..
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login extends Application {

    private final int GAP  = 10;
    private final int INSETS  = 25;
    private final int SCENE_WIDTH = 400;
    private final int SCENE_HEIGHT = 300;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();

        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Artatawe");

        primaryStage.show();
    }
}
