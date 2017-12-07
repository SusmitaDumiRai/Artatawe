package co.uk.artatawe.controller;

import co.uk.artatawe.database.AuctionDatabaseManager;
import co.uk.artatawe.database.BidDatabaseManager;
import co.uk.artatawe.main.Auction;
import co.uk.artatawe.main.Bid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for bid history page.
 * @author James Finlayson 905234
 * @author 908928
 */
public class BidHistoryController implements Initializable {

    private String username; //logged in user.
    private final int WIDTH = 800; //size of window.
    private final int HEIGHT = 600; //size of window.

    //Empty constructor.
    public BidHistoryController() {

    }

    /**
     * Sets username.
     * @param username username of logged in user.
     */
    public BidHistoryController(String username) {
        this.username = username;
    }

    @FXML
    private Button buyingHistoryButton;

    @FXML
    private Button sellingHistoryButton;

    @FXML
    private Label topLabel;

    @FXML
    private Button bidHistoryButton;

    @FXML
    private Label bottomLabel;

    @FXML
    private ListView<?> topList;

    @FXML
    private Button myActionsButton;

    @FXML
    private ListView<?> bottomList;

    @FXML
    private Pane pane;

    /*
    @FXML
    private Button auctionButton;

    @FXML
    private Button bidHistoryButton;


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
    */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    //    placedBidsList.setVisible(false);
 //       wonAuctionsList.setVisible(false);
     //   soldAuctionsList.setVisible(false);
        topLabel.setText("Placed bids");
        bottomLabel.setText("Won auctions");

        populateWonAuction(); //display won auctions.
        populateBidHistory(); //display past bids placed.


        //Label1.setStyle("-fx-text-fill: transparent"); //or .setVisible(false);
        //Label2.setStyle("-fx-text-fill: transparent"); //or .setVisible(false);
    }

    /**
     * Gets list of won auctions for logged in user.
     */
    public ObservableList<Auction> getWonAuctions() {
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

        //TODO TESTING WHEN POSSIBLE.
      //  String sqlSelect = "Select * from auction, bid where auctionComp = 1 and auction.auctionid = bid.auctionid and buyer = '" + this.username + "';";
        String sqlSelect = "Select * from auction, bid where auctionComp = 1 and auction.auctionid = bid.auctionid and buyer = 'username';";
        return  FXCollections.observableArrayList(auctionDatabaseManager.getAllAuctions(sqlSelect));

    }

    /**
     * Gets list of placed bids for logged in user.
     */
    public ObservableList<Bid> getPlacedBids() {

        BidDatabaseManager bidDatabaseManager = new BidDatabaseManager();

      //  String sqlSelect = "Select * from bid where buyer = '" + this.username + "';";

        String sqlSelect = "Select * from bid where buyer = 'username';";

        return FXCollections.observableArrayList(bidDatabaseManager.getAllBids(sqlSelect));

    }

    /**
     * Gets list of sold auctions for logged in user.
     */
    public ObservableList<Auction> getSoldAuctions() {

        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

   //   String sqlSelect = "Select * from auction where auctioncomp = 1 and seller = '" + this.username + "';";

        String sqlSelect = "Select * from auction where auctioncomp = true and seller = 'username';";
        return FXCollections.observableArrayList(auctionDatabaseManager.getAllAuctions(sqlSelect));



    }

    /**
     * Gets all auctions won by logged in user.
     * Displays all won auctions.
     */
    public void populateWonAuction() {
        ListView<Auction> auctionListView = new ListView<>(getWonAuctions());

        auctionListView.setCellFactory(param -> new ListCell<Auction>() {
            @Override
            protected void updateItem(Auction auction, boolean empty) {
                super.updateItem(auction, empty);

                if (empty || auction == null || auction.getArtwork() == null) {
                    setText(null);
                } else {
                    setText("Artwork title: " + auction.getArtwork().getTitle() +
                            "\nBid amount: " + auction.getHighestBid());
                }
            }
        });

        //TODO make it look nice
        auctionListView.setLayoutX(100);
        auctionListView.setLayoutY(369);
        pane.getChildren().add(auctionListView);
    }

    /**
     * Displays entire bid history of user,
     * in order chronological order.
     */
    public void populateBidHistory() {
        ListView<Bid> bidListView = new ListView<>(getPlacedBids());

        bidListView.setCellFactory(param -> new ListCell<Bid>() {
            @Override
            protected void updateItem(Bid bid, boolean empty) {
                super.updateItem(bid, empty);

                if (empty || bid == null || bid.getBuyer() == null) {
                    setText(null);
                } else {
                    AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();
                    String sqlSelect = "SELECT * FROM AUCTION WHERE AUCTIONID = " + bid.getAuctionID();
                    Auction auction = auctionDatabaseManager.getAuction(sqlSelect);

                    setText("Artwork title: " + auction.getArtwork().getTitle() +
                            "\nBid amount: " + Double.toString(bid.getBidAmount()) +
                            "\nBid date and time: " + bid.getDateAndTime());
                }
            }
        });


        //TODO FOR YOU TO MAKE IT LOOK NICE LENI
        bidListView.setLayoutX(100);
       bidListView.setLayoutY(120);
        pane.getChildren().add(bidListView);
    }

    /**
     * Gets all auctions sold by the logged in user.
     */
    public void populateSoldAuction() {
        ListView<Auction> auctionListView = new ListView<>(getWonAuctions());

        auctionListView.setCellFactory(param -> new ListCell<Auction>() {
            @Override
            protected void updateItem(Auction auction, boolean empty) {
                super.updateItem(auction, empty);

                if (empty || auction == null || auction.getArtwork() == null) {
                    setText(null);
                } else {
                    setText("Artwork title: " + auction.getArtwork().getTitle() +
                            "\nWinner username: " + auction.getWinner().getUserName() +
                            "\nBid amount: " + auction.getHighestBid());
                }
            }
        });

        //TODO make it look nice
        auctionListView.setLayoutX(100);
        auctionListView.setLayoutY(121);
        pane.getChildren().add(auctionListView);
    }

    @FXML
    void boughtHistoryAction(ActionEvent event) {
      //  pane.getChildren().clear(); //remove old details.
        populateBidHistory();
        populateWonAuction();
    }

    @FXML
    void soldHistoryAction(ActionEvent event) {
    //    pane.getChildren().clear(); //remove old details.
        populateSoldAuction();
    }



}
