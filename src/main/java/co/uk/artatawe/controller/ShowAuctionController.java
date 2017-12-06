package co.uk.artatawe.controller;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.artwork.Sculpture;
import co.uk.artatawe.database.ArtworkDatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Arc2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.ResourceBundle;

/**
 * Handles show auction fxml file.
 * Displays detailed information about certain artwork.
 *
 * @author 908928
 */
public class ShowAuctionController implements Initializable {

    private String username; //logged in user.
    private String photo; //selected artwork.

    @FXML
    private Label date;

    @FXML
    private Button makeBidButton;

    @FXML
    private Label creator;


    @FXML
    private Label year;

    @FXML
    private Label sellerName;

    @FXML
    private Button heartButton;

    @FXML
    private Label description;

    @FXML
    private Label title;

    @FXML
    private ImageView heart;

    @FXML
    private Label depth;

    @FXML
    private Label material;

    @FXML
    private Label width;

    @FXML
    private Label xDepth;

    @FXML
    private TextField bid;

    @FXML
    private Label reservedPrice;

    @FXML
    private Label height;

    @FXML
    private Label materialLabel;

    @FXML
    private ImageView imageViewPhoto;




    /**
     * Empty constructor.
     */
    public ShowAuctionController() {
    }

    /**
     * Creates controller object.
     * @param username username of logged in user.
     */
    public ShowAuctionController(String username, String photo) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xDepth.setVisible(false);
        depth.setVisible(false);
        material.setVisible(false);
        materialLabel.setVisible(false);

        getArtwork();
    }

    public void getArtwork() {
       String sql = "SELECT * from artwork where photo = '" + this.photo + "';";
      //  String sql = "SELECT * from artwork where photo = 'co/uk/artatawe/artworkpictures/FLIGHTLESS BIRD FROM FAIRY TALE 1.jpg';";

        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        ArrayList<Artwork> artworkArrayList = artworkDatabaseManager.getAllArtworks(sql); //returns one artwork, photo = UNIQUE.

        for (Artwork artwork : artworkArrayList) {

            this.title.setText(artwork.getTitle());
            this.description.setText(artwork.getDescription());
            this.width.setText(Double.toString(artwork.getWidth()));
            this.height.setText(Double.toString(artwork.getHeight()));
            this.reservedPrice.setText(Double.toString(artwork.getReservedPrice()));


           Image image = new Image(artwork.getPhoto());
            this.imageViewPhoto.setImage(image);

            if (artwork.getTypeOfArtwork().equals("sculpture")) {
                Sculpture sculpture = (Sculpture) artwork;
                this.xDepth.setVisible(true);
                depth.setVisible(true);
                material.setVisible(true);
                materialLabel.setVisible(true);

                depth.setText(Double.toString(sculpture.getDepth()));
                material.setText(sculpture.getMainMaterial());

            }


        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
