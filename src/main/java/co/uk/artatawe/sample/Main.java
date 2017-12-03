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
        //Image image = new Image("file:///D:/Git/Artatawe/src/main/java/co/uk/artatawe/artworkpictures/Giraffe.png");
        //ImageView imageView = new ImageView();
        //imageView.setImage(image);

        String imagePath = "co/uk/artatawe/artworkpictures/Giraffe.png";
        ImageView imageView = new ImageView(imagePath);


        // Display image on screen
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

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

     /*   String sqlInsertArtwork = "INSERT INTO artwork (title, description, photo, nameofcreator, reservedprice," +
        "dateentered, bidsallowed, typeofartwork, width, height) values ('Bar reflection', 'This piece is themed on two young people lost in each other, sitting in a mirrored booth in a public house - in the style he is now well known for. The back of the painting is signed by the artist, and both young people, Carra and Luke who sat for the creation of this work.'," +
                "'co/uk/artatawe/artworkpictures/Bar reflections.jpg'," +
                "'Kevin Day', '100', '01/02/2017', '20', 'painting', '76', '60' );";
*/

      //  artworkDatabaseManager.executeStatement(sqlInsertArtwork);

    }

}
