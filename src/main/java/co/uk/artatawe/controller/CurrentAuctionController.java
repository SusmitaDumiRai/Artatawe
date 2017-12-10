package co.uk.artatawe.controller;

import co.uk.artatawe.database.AuctionDatabaseManager;
import co.uk.artatawe.database.BidDatabaseManager;
import co.uk.artatawe.main.Auction;
import co.uk.artatawe.main.Bid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

    private final int TEXT = 15; //size of text.
    private final int WIDTH = 500; //size of list.
    private final int HEIGHT = 230; //size of list.
    private final int AUCTION_WITH_BID_LAYOUT_Y = 25; //Y location of list for displaying auctions with bid.
    private final int AUCTION_WITH_NO_BID_LAYOUT_Y = 300; //Y location of for displaying auctions without bid.
    private final int ONGOING_AUCTION_MESSAGE_LAYOUT_Y = 270; //Y location of "ongoing auction"
    private String username; //logged in user.

    @FXML
    private Pane pane;

    /**
     * Empty constructor.
     */
    public CurrentAuctionController() {
    }

    /**
     * Creates controller object.
     *
     * @param username username of logged in user.
     */
    public CurrentAuctionController(String username) {
        this.username = username;
    }

    /**
     * Display all ongoing auctions.
     *
     * @param location  location.
     * @param resources resources.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateSellingAuctionWithBid();
        populateSellingAuctionWithNoBid();
    }


    /**
     * Gets all ongoing auctions where the other user has placed bids.
     *
     * @return array list of bids.
     */
    public ObservableList<Bid> getSellingAuctionsWithBids() {
        String sqlSelect = "SELECT * from auction, bid where auction.auctionid = bid.auctionid and auctioncomp = 0 and seller = '" + this.username + "';";

        return FXCollections.observableArrayList(new BidDatabaseManager().getAllBids(sqlSelect));
    }

    /**
     * Gets all ongoing auctions where there are no bids.
     *
     * @return array list of auctions with no bids placed.
     */
    public ObservableList<Auction> getSellingAuctionsWithNoBids() {
        String sqlSelect = "select * from auction where (auctionid) not in (select auctionid from bid) and auctioncomp = 0;";

        return FXCollections.observableArrayList(new AuctionDatabaseManager().getAllAuctions(sqlSelect));
    }


    /**
     * Display bid details for an ongoing auction.
     */
    public void populateSellingAuctionWithBid() {
        ListView<Bid> bidListView = new ListView<>(getSellingAuctionsWithBids());

        bidListView.setCellFactory(param -> new ListCell<Bid>() {
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

        Label informationText = new Label();
        informationText.setText("People who have placed bids on your ongoing auction"); //display label.
        informationText.setFont(Font.font("Verdana", FontWeight.BOLD, TEXT));
        informationText.setAlignment(Pos.TOP_LEFT);

        bidListView.setPrefSize(WIDTH, HEIGHT);
        bidListView.setLayoutY(AUCTION_WITH_BID_LAYOUT_Y);

        pane.getChildren().add(informationText);
        pane.getChildren().add(bidListView);
    }


    /**
     * Display all ongoing auctions with no bids.
     */
    public void populateSellingAuctionWithNoBid() {
        ListView<Auction> auctionListView = new ListView<>(getSellingAuctionsWithNoBids());

        auctionListView.setCellFactory(param -> new ListCell<Auction>() {
            @Override
            protected void updateItem(Auction auction, boolean empty) {
                super.updateItem(auction, empty);

                if (empty || auction == null || auction.getArtwork() == null) {
                    setText(null);
                } else {
                    setText("Artwork title: " + auction.getArtwork().getTitle()); //just display artwork name.
                }
            }
        });

        Label informationText = new Label();
        informationText.setText("Ongoing auctions with no bids.");
        informationText.setFont(Font.font("Verdana", FontWeight.BOLD, TEXT));
        informationText.setTranslateY(ONGOING_AUCTION_MESSAGE_LAYOUT_Y);

        auctionListView.setPrefSize(WIDTH, HEIGHT);
        auctionListView.setLayoutY(AUCTION_WITH_NO_BID_LAYOUT_Y);

        pane.getChildren().add(informationText);
        pane.getChildren().add(auctionListView);

    }

    /**
     * Gets logged in username.
     *
     * @return username of logged in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets logged in user's username.
     *
     * @param username username of logged in user.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
