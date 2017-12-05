package co.uk.artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.sample.Main;

/**
 * Controller class for browse auction.
 *
 * @author 908928
 */
public class BrowseAuctionController implements Initializable {

    private String username;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;


    @FXML
    private TilePane artworkTilePane;

    @FXML
    private ScrollPane artworkScrollPane;

    @FXML
    private ImageView imv;
    
    public void getImages() {

        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        ArrayList<Artwork> artworkArrayList = artworkDatabaseManager.getAllArtworks();

        ArrayList<String> artworkPhoto = new ArrayList<>();

        Image[] images = new Image[artworkArrayList.size()];
        ImageView[] imageViews = new ImageView[artworkArrayList.size()];
        VBox[] vBoxes = new VBox[artworkArrayList.size()]; //vboxs to add in grid pane.

        //Get location of artwork photos.
        for (Artwork artwork : artworkArrayList) {
            artworkPhoto.add(artwork.getPhoto());
        }
        Rectangle2D viewportRect = new Rectangle2D(40, 35, 110, 110);

        System.out.println(artworkArrayList.size());


        String[] imageLocation = artworkPhoto.toArray(new String[artworkArrayList.size()]); //convert array list to array.

        for (int i = 0; i < imageLocation.length; i++) {
            images[i] = new Image(imageLocation[i]); //get image.
            imageViews[i] = new ImageView(images[i]); //add image to image view.
            //add some i guess details here.
            imageViews[i].setViewport(viewportRect);
            vBoxes[i] = new VBox();
            vBoxes[i].getChildren().add(imageViews[i]); //add vbox inside gridpane.
            artworkTilePane.getChildren().add(vBoxes[i]); //add image to gridpane.

        }





      //  Image[] images = new Image[artworkArrayList.size()].getClass().getResourceAsStream(imageLocation);










        /**
        StackPane parent = new StackPane();

    	    for (Artwork a : artworks) {
    	    	images.add(new ImageView(a.getPhoto()));
    	    }
    	    
    	    for(ImageView i : images) {
    	    	parent.getChildren().add(i);
    	    }
    	    
    	    final Image image2 = new Image(Main.class.getResourceAsStream("..//artworkpictures//Demeter 1.png"));
            imv.setImage(image2);
         **/
    }
    
    public BrowseAuctionController() {
    }

    public BrowseAuctionController(String username) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getImages();
    }

    /**
     * Displays profile page when clicked.
     * @param event
     */
    @FXML
    void handleProfileAction(ActionEvent event) {

        System.out.println("My Profile clicked!");
    }

    @FXML
    void handleAuctionAction(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CreateAuction.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new auction");
            stage.setScene(new Scene(root, WIDTH, HEIGHT));

            CreateAuctionController createAuctionController = fxmlLoader.getController();

            createAuctionController.changeSellerUsername(this.username);

            stage.show(); //display create auctions.

            //hides current window.
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }



}
