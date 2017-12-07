package co.uk.artatawe.controller;

import co.uk.artatawe.database.AuctionDatabaseManager;
import co.uk.artatawe.database.BidDatabaseManager;
import co.uk.artatawe.main.Auction;
import co.uk.artatawe.main.Bid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Controller for bid history page.
 * @author James Finlayson 905234
 */
public class BidHistoryController implements Initializable {

    private String username; //logged in user.
    private final int WIDTH = 800; //size of window.
    private final int HEIGHT = 600; //size of window.

    /**
     * Sets username.
     * @param username username of logged in user.
     */
    public BidHistoryController(String username) {
        this.username = username;
    }

    @FXML
    private Button auctionButton;

    @FXML
    private Button bidHistoryButton;

    @FXML
    private Button Button;

    @FXML
    private Button buyingHistoryButton;

    @FXML
    private Button usersButton;

    @FXML
    private Button sellingHistoryButton;

    @FXML
    private ListView<Auction> wonAuctionsList;

    ObservableList<Auction> wonAuctions = FXCollections.observableArrayList(getWonAuctions());

    @FXML
    private Label welcomeText;

    @FXML
    private Button myActionsButton;

    @FXML
    private ListView<Bid> placedBidsList;

    ObservableList<Bid> placedBids = FXCollections.observableArrayList(getPlacedBids());

    @FXML
    private ListView<Auction> soldAuctionsList;

    ObservableList<Auction> soldAuctions = FXCollections.observableArrayList(getSoldAuctions());

    @FXML
    private Button profileButton;

    /**
     * Gets list of won auctions for logged in user.
     */
    public ArrayList getWonAuctions(){

        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

        String sqlSelect = "Select * from auction where auctionComp = 1 and winningBid = '" + this.username + "';";

        ArrayList<Auction> wonAuctionArrayList = auctionDatabaseManager.getAllAuctions(sqlSelect);

        return wonAuctionArrayList;

    }

    /**
     * Gets list of placed bids for logged in user.
     */
    public ArrayList getPlacedBids(){

        BidDatabaseManager bidDatabaseManager = new BidDatabaseManager();

        String sqlSelect = "Select * from bid where buyer = '" + this.username + "';";

        ArrayList<Bid> placedBidsArrayList = bidDatabaseManager.getAllBids(sqlSelect);

        return placedBidsArrayList;

    }

    /**
     * Gets list of sold auctions for logged in user.
     */
    public ArrayList getSoldAuctions(){

        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

        String sqlSelect = "Select * from auction where auctioncomp = true and seller = '" + this.username + "';";

        ArrayList<Auction> soldAuctionsArrayList = auctionDatabaseManager.getAllAuctions(sqlSelect);

        return soldAuctionsArrayList;

    }

    @FXML
    void handleBuyingHistoryButton(ActionEvent event){
        placedBidsList.setVisible(true);
        wonAuctionsList.setVisible(true);

    }

    @FXML
    void handleSellingHistoryButton(ActionEvent event){
        soldAuctionsList.setVisible(true);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        placedBidsList.setVisible(false);
        wonAuctionsList.setVisible(false);
        soldAuctionsList.setVisible(false);
    }
}
