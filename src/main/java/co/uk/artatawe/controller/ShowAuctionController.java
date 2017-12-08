package co.uk.artatawe.controller;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.artwork.Sculpture;
import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.AuctionDatabaseManager;
import co.uk.artatawe.database.BidDatabaseManager;
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
//import sun.awt.geom.AreaOp;

import java.awt.geom.Arc2D;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private Artwork artwork;
    private Auction auction;

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

    @FXML
    private Label errorMessage;

    @FXML
    private Label numOfBidLeft;


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
     //   String sql = "SELECT * from artwork where photo = 'co/uk/artatawe/artworkpictures/FLIGHTLESS BIRD FROM FAIRY TALE 1.jpg';";

        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

        String sqlSelectArtwork = "Select * from artwork where artwork.photo = '" + this.photo + "';";
        artwork = artworkDatabaseManager.getArtwork(sqlSelectArtwork);

        String sqlSelectAuction = "select * from auction where auctionid = " +  artwork.getArtworkID() + ";";
        auction = auctionDatabaseManager.getAuction(sqlSelectAuction);


        //Display all information in the GUI.
        this.title.setText(artwork.getTitle());
        this.description.setText(artwork.getDescription());
        this.width.setText(Double.toString(artwork.getWidth()));
        this.height.setText(Double.toString(artwork.getHeight()));
        this.reservedPrice.setText(Double.toString(auction.getHighestBid()));
        this.date.setText(artwork.getDateEntered());
        this.creator.setText(artwork.getNameOfCreator());
        this.sellerName.setText(auction.getSeller().getUserName());
        this.numOfBidLeft.setText(Integer.toString(auction.getNumOfBidsLeft()));


        //Display image.
        Image image = new Image(artwork.getPhoto());
        this.imageViewPhoto.setImage(image);

        if (artwork.getTypeOfArtwork().equals("sculpture")) { //if sculpture display more info.
            //TODO EXTRA PHOTO.
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
        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        //Insert into bid.
        if (valMakeBid()) {
            String sqlInsert = "INSERT INTO BID (auctionid, buyer, bidamount, dateandtime) values (" + artwork.getArtworkID() + ", '" +
                    this.username + "'," + bid.getText() + ", '" + dateFormat.format(date) + "');";

            BidDatabaseManager bidDatabaseManager = new BidDatabaseManager();
            bidDatabaseManager.executeStatement(sqlInsert);


            String sqlUpdateBidAmount = "";
            //Decrease bid amount.
            if (auction.getNumOfBidsLeft() > 1) {
                sqlUpdateBidAmount = "Update auction set numofbidsleft = " + (auction.getNumOfBidsLeft() - 1) + " where auctionid = " + this.artwork.getArtworkID() + ";";

            } else { //complete auction.
                sqlUpdateBidAmount = "Update auction set numofbidsleft = " + (auction.getNumOfBidsLeft() - 1) + ", auctioncomp = 1 where auctionid = " + this.artwork.getArtworkID() + ";";
                makeBidButton.setDisable(true);
            }

            numOfBidLeft.setText(Integer.toString(auction.getNumOfBidsLeft() - 1)); //update num of bids left in GUI.
            reservedPrice.setText(bid.getText());


            auctionDatabaseManager.updateStatement(sqlUpdateBidAmount);

        }
    }

    /**
     * Validates bid.
     * If it is a double and greater than the highest bid.
     * @return
     */
    public boolean valMakeBid() {
        try {
            Double.parseDouble(bid.getText());
            if (Double.parseDouble(bid.getText()) > Double.parseDouble(reservedPrice.getText())) {
                return true;
            } else {
                System.out.println("bid price too low"); //TODO ERROR MESAGE.
                errorMessage.setText("'Bid is too low'");
                errorMessage.setTextFill(Paint.valueOf("RED"));
                return  false;
            }

        } catch (NumberFormatException ex) {
            System.out.println("enter digits only please"); // needs to be cahnged.
            //TODO display error message
            errorMessage.setText("'Enter digits only'");
            errorMessage.setTextFill(Paint.valueOf("RED"));
        }
        return false;


    }
}
