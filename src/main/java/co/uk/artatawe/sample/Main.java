package co.uk.artatawe.sample;

import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.AuctionDatabaseManager;
import co.uk.artatawe.database.BidDatabaseManager;
import co.uk.artatawe.database.UserDatabaseManager;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();


        try {


            String imagePath = "co/uk/artatawe/artworkpictures/Giraffe.png";
            ImageView imageView = new ImageView(imagePath);


        //   Image image = new Image("//co//uk//artatawe//artworkpictures//Lion 1.jpg");
         //   ImageView imageView = new ImageView(image);
            root.getChildren().add(imageView);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }





        // Display image on screen

        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Image Read Test");
        primaryStage.setScene(scene);
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
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();
        BidDatabaseManager bidDatabaseManager = new BidDatabaseManager();

        userDatabaseManager.getAllUsers();

        String sqlInsertArtwork = "INSERT INTO artwork (title, description, photo, nameofcreator, reservedprice," +
        "dateentered, bidsallowed, typeofartwork, width, height) values ('The Dangers of Late Night Mushroom Picking'," +
                "'Unfortunately not everyone or everything is what it seems. Trust your instincts.'," +
                "'co/uk/artatawe/artworkpictures/The dangers of late night mushroom picking.jpg'," +
                "'Bobby Chiu', '205', '30/03/2017', '15', 'painting', '5.5', '6.6' );";
      artworkDatabaseManager.executeStatement(sqlInsertArtwork);

        artworkDatabaseManager.getAllArtworks();


    }

}
