package co.uk.artatawe.controller;

import co.uk.artatawe.database.BidDatabaseManager;
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
    private final int HEIGHT = 500; //size of list.

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateSellingAuction();
    }


    /**
     * Get all bids being placed on an auction being sold by user.
     */
    public ObservableList<Bid> getSellingAuctions() {
        String sqlSelect = "SELECT * from auction, bid where auction.auctionid = bid.auctionid and auctioncomp = 0 and seller = '" + this.username + "';";
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
        Label informationText = new Label();
        informationText.setText("People who have placed bids on your ongoing auction");
        informationText.setFont(Font.font("Verdana", FontWeight.BOLD, TEXT));
        informationText.setAlignment(Pos.TOP_LEFT);

        auctionListView.setPrefSize(WIDTH, HEIGHT);
        auctionListView.setLayoutY(25);

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
