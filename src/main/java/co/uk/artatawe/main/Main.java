package co.uk.artatawe.main;

import co.uk.artatawe.database.*;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;


//TODO DELETE THIS CLASS
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();

        /*
        try {

         //   String imagePath = "file:///D:\\Susi\\Documents\\University\\Level 2\\CS-230 Software engineering\\Artatawe\\src\\main\\java\\co\\uk\\artatawe\\artworkpictures\\Solstice 1.png";

       //     ImageView imageView = new ImageView(imagePath);


            //   ImageView imageView = new ImageView(image);
            root.getChildren().add(imageView);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


*/
        System.out.println("file:///" + "hello");
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



       // WatchingDatabaseManager watchingDatabaseManager = new WatchingDatabaseManager();
       // String sql = "update watching set username = 'username' where username = ' username';";
        //watchingDatabaseManager.updateStatement(sql);
        //watchingDatabaseManager.createWatchingTable();




        //   System.out.println(bidDatabaseManager.getMaxBid(4));
        // String sql = "UPDATE auction set winningbid = 24 where auctionid = 4";
        // bidDatabaseManager.updateStatement(sql);

        //String sql = "update artwork set description = 'An unique handmade sculpture made from bright chamotte clay. Coated with glaze.' where title = 'FLIGHTLESS BIRD FROM FAIRY TALE'; ";
        //artworkDatabaseManager.updateStatement(sql);

        //   String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid and auctioncomp = 0;";

//      String sqlSelect = "SELECT * FROM ARTWORK;";

        //  String sql = "update bid set buyer = 'uglybackpack' where bidid = 4;";

        //    bidDatabaseManager.updateStatement(sql);

        //    auctionDatabaseManager.updateStatement(sql);
        //    auctionDatabaseManager.getAllAuctions("select * from auction;");
        //   artworkDatabaseManager.getAllArtworks(sqlSelect);
        //    auctionDatabaseManager.getAllAuctions();
        System.out.println("hello");


    }



}
