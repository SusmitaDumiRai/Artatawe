package co.uk.artatawe.sample;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.database.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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



       /* String imagePath = "co/uk/artatawe/artworkpictures/Giraffe.png";
        ImageView imageView = new ImageView(imagePath);*/


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
        FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();




     // String sql = "update from artwork set artworkid = 10 where title = 'Giraffe'; ";
       //userDatabaseManager.updateStatement(sql);

     //   String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid and auctioncomp = 0;";
       // artworkDatabaseManager.getAllArtworks(sqlSelect);
    //    auctionDatabaseManager.getAllAuctions();

    }



}
