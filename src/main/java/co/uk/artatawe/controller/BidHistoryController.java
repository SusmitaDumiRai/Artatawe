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
 *
 * @author James Finlayson 905234
 * @author 908928 - Susmita
 */
public class BidHistoryController implements Initializable {

    private String username; //logged in user.
    private final int WIDTH = 520; //size of window.
    private final int HEIGHT = 220; //size of window.
    private final int TOP_LIST_LAYOUT_Y = 120; //Y location of bid history list + sold auction list.
    private final int BOTTOM_LIST_LAYOUT_Y = 369; //Y location of won auction list.


    @FXML
    private Label topLabel;

    @FXML
    private Label bottomLabel;

    @FXML
    private Pane pane;

    @FXML
    private ListView<Bid> bidListView;

    @FXML
    private ListView<Auction> auctionListView;

    @FXML
    private ListView<Auction> soldAuctionListView;

    @FXML
    private Button buyingHistoryButton;

    @FXML
    private Button sellingHistoryButton;

    //Empty constructor.
    public BidHistoryController() {

    }

    /**
     * Sets username.
     *
     * @param username username of logged in user.
     */
    public BidHistoryController(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateWonAuction(); //display won auctions.
        populateBidHistory(); //display past bids placed.
    }

    /**
     * Gets list of won auctions for logged in user.
     *
     * @return array list of auctions won by user.
     */
    public ObservableList<Auction> getWonAuctions() {
        String sqlSelect = "select auction.auctionid, seller, numofbidsleft, highestbid, auctioncomp, winningbid" +
                " from auction, bid where auction.auctionid = bid.bidid and auctioncomp = 1 " +
                "and auction.winningbid in (select bidid from bid where buyer = '" + this.username + "');";
        return FXCollections.observableArrayList(new AuctionDatabaseManager().getAllAuctions(sqlSelect));

    }

    /**
     * Gets list of placed bids for logged in user.
     *
     * @return array list of bids placed by user.
     */
    public ObservableList<Bid> getPlacedBids() {
        String sqlSelect = "Select * from bid where buyer = '" + this.username + "';";
        return FXCollections.observableArrayList(new BidDatabaseManager().getAllBids(sqlSelect));
    }

    /**
     * Gets list of sold auctions for logged in user.
     *
     * @return array list of auctions sold by user.
     */
    public ObservableList<Auction> getSoldAuctions() {
        String sqlSelect = "Select * from auction where auctioncomp = 1 and seller = '" + this.username + "';";
        return FXCollections.observableArrayList(new AuctionDatabaseManager().getAllAuctions(sqlSelect));

    }

    /**
     * Gets all auctions won by logged in user.
     * Displays all won auctions.
     */
    public void populateWonAuction() {
        auctionListView = new ListView<>(getWonAuctions());

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

        topLabel.setText("Placed Bids");
        auctionListView.setPrefSize(WIDTH, HEIGHT);
        auctionListView.setLayoutY(BOTTOM_LIST_LAYOUT_Y);

        pane.getChildren().add(auctionListView);
    }

    /**
     * Displays entire bid history of user,
     * in order chronological order.
     */
    public void populateBidHistory() {
        bidListView = new ListView<>(getPlacedBids());

        bidListView.setCellFactory(param -> new ListCell<Bid>() {
            @Override
            protected void updateItem(Bid bid, boolean empty) {
                super.updateItem(bid, empty);

                if (empty || bid == null || bid.getBuyer() == null) {
                    setText(null);
                } else {
                    AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();
                    String sqlSelect = "SELECT * FROM AUCTION WHERE AUCTIONID = " + bid.getAuction().getArtwork().getArtworkID();
                    Auction auction = auctionDatabaseManager.getAuction(sqlSelect);

                    setText("Artwork title: " + auction.getArtwork().getTitle() +
                            "\nBid amount: " + Double.toString(bid.getBidAmount()) +
                            "\nBid date and time: " + bid.getDateAndTime());
                }
            }
        });

        bottomLabel.setText("Won auctions");
        bottomLabel.setVisible(true);
        bidListView.setPrefSize(WIDTH, HEIGHT);
        bidListView.setLayoutY(TOP_LIST_LAYOUT_Y);
        pane.getChildren().add(bidListView);
    }

    /**
     * Gets all auctions sold by the logged in user.
     */
    public void populateSoldAuction() {
        soldAuctionListView = new ListView<>(getSoldAuctions());

        soldAuctionListView.setCellFactory(param -> new ListCell<Auction>() {
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

        bidListView.setVisible(false);
        auctionListView.setVisible(false);
        bottomLabel.setVisible(false);
        topLabel.setText("Sold auctions");
        soldAuctionListView.setPrefSize(WIDTH, HEIGHT);
        soldAuctionListView.setLayoutY(TOP_LIST_LAYOUT_Y);
        pane.getChildren().add(soldAuctionListView);
    }

    @FXML
    void boughtHistoryAction(ActionEvent event) {
        pane.getChildren().clear(); //remove old details.
        pane.getChildren().add(buyingHistoryButton);
        pane.getChildren().add(sellingHistoryButton);
        pane.getChildren().add(topLabel);
        pane.getChildren().add(bottomLabel);
        populateBidHistory();
        populateWonAuction();
    }

    @FXML
    void soldHistoryAction(ActionEvent event) {
        pane.getChildren().clear(); //remove old details.
        pane.getChildren().add(buyingHistoryButton);
        pane.getChildren().add(sellingHistoryButton);
        pane.getChildren().add(topLabel);
        populateSoldAuction();
    }

    /**
     * Get username of logged in user.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username of logged in user.
     *
     * @param username username.
     */
    public void setUsername(String username) {
        this.username = username;
    }


}
