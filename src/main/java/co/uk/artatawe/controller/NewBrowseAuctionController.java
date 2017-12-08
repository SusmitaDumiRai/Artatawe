package co.uk.artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.database.ArtworkDatabaseManager;

/**
 * Controller class for browse auction.
 *
 * @author 908928
 * @author Plamena Tseneva
 */
public class NewBrowseAuctionController implements Initializable {

    private String username; //logged in user.

    @FXML
    private TilePane artworkTilePane;

    @FXML
    private ScrollPane artworkScrollPane;

    @FXML
    private ImageView imv;



    /**
     * Empty constructor.
     */
    public NewBrowseAuctionController() {
    }

    /**
     * Sets username.
     * @param username username of logged in user.
     */
    public NewBrowseAuctionController(String username) {
        this.username = username;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getImages();
    }


    /**
     * Gets all artworks currently in auction. Displays them.
     */
    public void getImages() {
        Stage stage = new Stage();

        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();

        String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid and auctioncomp = 0;";

        ArrayList<Artwork> artworkArrayList = artworkDatabaseManager.getAllArtworks(sqlSelect); //get ongoing artworks.

        ArrayList<String> artworkPhoto = new ArrayList<>();

        Image[] images = new Image[artworkArrayList.size()];
        ImageView[] imageViews = new ImageView[artworkArrayList.size()];
        VBox[] vBoxes = new VBox[artworkArrayList.size()]; //vboxs to add in grid pane.


        artworkScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); //scroller can't move horizontally.
        artworkScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); //scroller can move vertically.
        artworkScrollPane.setFitToHeight(true);
        artworkScrollPane.setContent(artworkTilePane);

        //Get location of artwork photos.
        for (Artwork artwork : artworkArrayList) {
            artworkPhoto.add(artwork.getPhoto());
        }
        //Rectangle2D viewportRect = new Rectangle2D(40, 35, 200, 200);//don't need this

        String[] imageLocation = artworkPhoto.toArray(new String[artworkArrayList.size()]); //convert array list to array.

        for (int i = 0; i < imageLocation.length; i++) {

            images[i] = new Image(imageLocation[i], 200, 0, true, true); //get image.
            imageViews[i] = new ImageView(images[i]); //add image to image view.
            imageViews[i].setFitWidth(200);
            imageViews[i].setFitHeight(stage.getHeight() - 10);
            imageViews[i].setPreserveRatio(true);
            imageViews[i].setSmooth(true);
            imageViews[i].setCache(true);

            //add some i guess details here.
            //imageViews[i].setViewport(viewportRect);//don't need this

            vBoxes[i] = new VBox();
            vBoxes[i].getChildren().addAll(imageViews[i]); //add vbox inside gridpane.
            artworkTilePane.getChildren().add(vBoxes[i]); //add image to gridpane.
            artworkTilePane.setAlignment(Pos.CENTER);

        }

    }


    /**
     * Displays profile page when clicked.
     * @param event event.
     */
   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
