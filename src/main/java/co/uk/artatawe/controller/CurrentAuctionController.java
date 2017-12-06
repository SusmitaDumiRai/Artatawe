package co.uk.artatawe.controller;

import co.uk.artatawe.database.ArtworkDatabaseManager;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Susi on 06/12/2017.
 */
public class CurrentAuctionController implements Initializable {

    private String username; //logged in user.

    /**
     * Empty constructor.
     */
    public CurrentAuctionController() {
    }

    /**
     * Creates controller object.
     * @param username username of logged in user.
     */
    public CurrentAuctionController(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getCurrentSellingAuctions();
    }

    /**
     * Displays all ongoing auctions being sold by logged in user.
     */
    public void getCurrentSellingAuctions() {

       // String sqlSelect = "SELECT * from artwork, auction where artwork.artworkid = auction.auctionid and auctioncomp = 0 and seller = '" + this.username + "';";
        String sqlSelect = "SELECT * from artwork, auction where artwork.artworkid = auction.auctionid and auctioncomp = 0 and seller = 'username';";

        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        artworkDatabaseManager.getAllArtworks(sqlSelect);




    }

    /**
     * Displays all bids logged in user has placed on an ongoing auction.
     */
    public void getCurrentBidsPlaced() {

    }
    /**
     * Gets logged in username.
     * @return username of logged in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets logged in user's username.
     * @param username username of logged in user.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
