package co.uk.artatawe.controller;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.artwork.Sculpture;
import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.AuctionDatabaseManager;
import co.uk.artatawe.main.Auction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcTo;
import sun.awt.geom.AreaOp;

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
        valMakeBid();
    }

    public void getArtwork() {
       String sql = "SELECT * from artwork where photo = '" + this.photo + "';";
     //   String sql = "SELECT * from artwork where photo = 'co/uk/artatawe/artworkpictures/FLIGHTLESS BIRD FROM FAIRY TALE 1.jpg';";

        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

        String sqlSelectArtwork = "Select * from artwork where artwork.photo = '" + this.photo + "';";
        Artwork artwork = artworkDatabaseManager.getArtwork(sqlSelectArtwork);

        String sqlSelectAuction = "select * from auction where auctionid = " +  artwork.getArtworkID() + ";";
        Auction auction = auctionDatabaseManager.getAuction(sqlSelectAuction);


        this.title.setText(artwork.getTitle());
        this.description.setText(artwork.getDescription());
        this.width.setText(Double.toString(artwork.getWidth()));
        this.height.setText(Double.toString(artwork.getHeight()));
        this.reservedPrice.setText(Double.toString(auction.getHighestBid()));
        this.date.setText(artwork.getDateEntered());
        this.creator.setText(artwork.getNameOfCreator());


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

    /**
     * Get the username.
     * @return username of logged in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username of logged in user.
     * @param username username of logged in user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get file location for the auction's photo.
     * @return file location of photo.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Set the location for the auction's photo.
     * @param photo file location of photo.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @FXML
    void handleButtonAction(ActionEvent event) {

    }

    /**
     * Validates bid.
     * If it is a double and greater than the highest bid.
     * @return
     */
    public boolean valMakeBid() {


        //String update = update auction set numofbidsleft = -1

        /**
         *   "auctionID INTEGER PRIMARY KEY not null,\n" +
         "seller text not null," + //username of seller.
         "winningBid int," + //winning bid id.
         "numOfBidsLeft integer not null," +
         "auctioncomp int not null," + //sqlite does not support boolean, but instead 0 and 1.
         "highestbid real not null,"  + //originally the reserve price.
         */


        /*
        "bidID INTEGER PRIMARY KEY not null," +
                "auctionID integer not null," +
                "buyer text not null," + //username of buyer.
                " bidAmount real not null," +
                " dateAndTime text not null," + //date and time of bid made.
                */
        try {
            Float.parseFloat(bid.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only please"); // needs to be cahnged.
            //TODO display error message
        }
        return false;


    }
}
