package co.uk.artatawe.controller;

import co.uk.artatawe.database.BidDatabaseManager;
import co.uk.artatawe.main.Bid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles current auction fxml file.
 * Displays all current auctions being sold and bids being placed
 * on ongoing auctions.
 *
 * @author 908928
 */
public class CurrentAuctionController implements Initializable {


    private String username; //logged in user.

    @FXML
    private TilePane tilePane;

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
        populateSellingAuction();
    }


    /**
     * Get all bids being placed on an auction being sold by user.
     */
    public ObservableList<Bid> getSellingAuctions() {
       // String sqlSelect = "SELECT * from auction, bid where auction.auctionid = bid.auctionid and auctioncomp = 0 and seller = '" + this.username + "';";
        String sqlSelect = "SELECT * from auction, bid where auction.auctionid = bid.auctionid and auctioncomp = 0 and seller = 'lolfan';";
        return FXCollections.observableArrayList(new BidDatabaseManager().getAllBids(sqlSelect));
    }

    /**
     * Display bid details for an ongoing auction.
     */
    public void populateSellingAuction() {
        ListView<Bid> auctionListView = new ListView<>(getSellingAuctions());

        auctionListView.setCellFactory(param -> new ListCell<Bid>() {
            @Override
            protected void updateItem(Bid bid, boolean empty) {
                super.updateItem(bid, empty);

                if (empty || bid == null || bid.getBuyer() == null) {
                    setText(null);
                } else {
                    setText("Artwork title: " + bid.getAuction().getArtwork().getTitle() +
                            "\nBidder: " + bid.getBuyer().getUserName() +
                            "\nBid amount: " + bid.getBidAmount() +
                            "\nBid date and time: " + bid.getDateAndTime());
                }
            }
        });

        //TODO make it look nice
        auctionListView.setLayoutX(100);
        auctionListView.setLayoutY(121);
        tilePane.getChildren().add(auctionListView);
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
