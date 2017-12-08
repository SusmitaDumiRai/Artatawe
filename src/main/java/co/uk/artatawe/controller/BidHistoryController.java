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
import javafx.geometry.Pos;
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
    private final int WIDTH = 520; //size of window.
    private final int HEIGHT = 220; //size of window.



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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        populateWonAuction(); //display won auctions.
        populateBidHistory(); //display past bids placed.
    }

    /**
     * Gets list of won auctions for logged in user.
     */
    public ObservableList<Auction> getWonAuctions() {


        //TODO TESTING WHEN POSSIBLE.
      //  String sqlSelect = "Select * from auction, bid where auctionComp = 1 and auction.auctionid = bid.auctionid and buyer = '" + this.username + "';";
        String sqlSelect = "Select * from auction, bid where auctionComp = 1 and auction.auctionid = bid.auctionid and buyer = 'username';";
        return  FXCollections.observableArrayList(new AuctionDatabaseManager().getAllAuctions(sqlSelect));

    }

    /**
     * Gets list of placed bids for logged in user.
     */
    public ObservableList<Bid> getPlacedBids() {


      //  String sqlSelect = "Select * from bid where buyer = '" + this.username + "';";

        String sqlSelect = "Select * from bid where buyer = 'username';";

        return FXCollections.observableArrayList(new BidDatabaseManager().getAllBids(sqlSelect));

    }

    /**
     * Gets list of sold auctions for logged in user.
     */
    public ObservableList<Auction> getSoldAuctions() {

        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

   //   String sqlSelect = "Select * from auction where auctioncomp = 1 and seller = '" + this.username + "';";

        String sqlSelect = "Select * from auction where auctioncomp = true and seller = 'username';";
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

        //TODO make it look nice
        topLabel.setText("Placed Bids");
        auctionListView.setPrefSize(WIDTH,HEIGHT);
        auctionListView.setLayoutY(369);
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


        //TODO FOR YOU TO MAKE IT LOOK NICE LENI
        bottomLabel.setText("Won auctions");
        bottomLabel.setVisible(true);
        bidListView.setPrefSize(WIDTH,HEIGHT);
        bidListView.setLayoutY(120);
        pane.getChildren().add(bidListView);
    }

    /**
     * Gets all auctions sold by the logged in user.
     */
    public void populateSoldAuction() {
        soldAuctionListView = new ListView<>(getWonAuctions());

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

        //TODO make it look nice
        bidListView.setVisible(false);
        auctionListView.setVisible(false);
        bottomLabel.setVisible(false);
        topLabel.setText("Sold auctions");
        soldAuctionListView.setPrefSize(WIDTH,HEIGHT);
        soldAuctionListView.setLayoutY(121);
        pane.getChildren().add(soldAuctionListView);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
