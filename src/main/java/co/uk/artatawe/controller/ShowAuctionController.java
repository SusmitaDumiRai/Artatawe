package co.uk.artatawe.controller;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.artwork.Sculpture;
import co.uk.artatawe.database.*;
import co.uk.artatawe.main.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Handles show auction fxml file.
 * Displays detailed information about certain artwork.
 * @author 908928 - Susmita
 * @author 914937 - Plamena
 */
public class ShowAuctionController implements Initializable {

    private String username; //logged in user.
    private String photo; //selected artwork.
    private Artwork artwork;
    private Auction auction;
    private int numPeopleWatching;

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

    @FXML
    private Label numWatchers;

    @FXML
    private Label successLabel;

    @FXML
    private ImageView watchIcon;




    /**
     * Empty constructor.
     */
    public ShowAuctionController() {
    }

    /**
     * Creates controller object.
     *
     * @param username username of logged in user.
     */
    public ShowAuctionController(String username, String photo) {
        this.username = username;
    }


    /**
     * Get all from watching table.
     * If user had previously watched this auction, add them to arraylist.
     * @return arraylist of whether user has watched this auction before or not.
     */
    public ArrayList<Watching> getWatching() {
        WatchingDatabaseManager watchingDatabaseManager = new WatchingDatabaseManager();
        String sqlSelect = "select * from watching;";
        ArrayList<Watching> watchingUsers = new ArrayList<>();

        for (Watching watching : watchingDatabaseManager.getAllWatching(sqlSelect)) {
            if (watching.getUsername().equals(this.username)) {
                watchingUsers.add(new Watching(watching.getAuctionID(), watching.getUsername()));
            }
        }
        return watchingUsers;
    }

    /**
     * Check to see if user is already a watcher.
     * @param user logged in user.
     * @return true if user has already watched this auction.
     */
    private boolean isWatching(User user) {
        for (Watching watcher : getWatching()) {
            if (watcher.getUsername().equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get all favourite sellers of logged in user.
     * @return arraylist of users favourited by logged in user.
     */
    public ArrayList<User> getAllFavouriteSellers() {

        FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();
        String sqlSelect = "select * from favouriteuser where username2 = '" + this.sellerName.getText() + "';";
        ArrayList<User> favSellers = new ArrayList<>();

        for (FavouriteUsers favouriteUsers : favouriteUserDatabaseManager.getFavouriteUsers(sqlSelect)) {
            if (favouriteUsers.getUser1().getUserName().equals(this.username)) {
                favSellers.add(favouriteUsers.getUser2());
            }
        }

        return favSellers;
    }

    /**
     * Checks whether a seller is a favourite user of the current user.
     * @param seller seller of auction.
     * @return true if the seller is from the list of favourites of the logged in user.
     */
    private boolean isFavouriteOf(User seller) {
        for (User favSeller : getAllFavouriteSellers()) {
            if (favSeller.getUserName().equals(seller.getUserName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get number of people watching this artwork.
     * Set the number of people watching.
     */
    public void getNumOfPeopleWatching() {
        String sqlSelect = "Select * from watching where auctionid = " + this.auction.getArtwork().getArtworkID() + ";";
        ArrayList<Watching> watchings = new WatchingDatabaseManager().getAllWatching(sqlSelect);
        numPeopleWatching = watchings.size();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xDepth.setVisible(false);
        depth.setVisible(false);
        material.setVisible(false);
        materialLabel.setVisible(false);

        getArtwork(); //display artwork.
        getNumOfPeopleWatching(); //get number of people watching.
        numWatchers.setText(Integer.toString(numPeopleWatching)); //set text to num of people watching.
    }

    /**
     * Get auction's artwork's details.
     */
    public void getArtwork() {

        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();

        String sqlSelectArtwork = "Select * from artwork where artwork.photo = '" + this.photo + "';";
        artwork = artworkDatabaseManager.getArtwork(sqlSelectArtwork);

        String sqlSelectAuction = "select * from auction where auctionid = " + artwork.getArtworkID() + ";";
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

        //If sculpture display more info.
        if (artwork.getTypeOfArtwork().equals("sculpture")) {
            Sculpture sculpture = (Sculpture) artwork;
            this.xDepth.setVisible(true);
            depth.setVisible(true);
            material.setVisible(true);
            materialLabel.setVisible(true);

            depth.setText(Double.toString(sculpture.getDepth()));
            material.setText(sculpture.getMainMaterial());

        }

        //Check whether the given seller is in user's favourites
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();



        //Check whether seller is favourite.

        String sqlSelect = "Select * from user where username = '" + this.sellerName.getText() + "';";
        for (User user: userDatabaseManager.getAllUsers(sqlSelect)) {
            if (isFavouriteOf(user)) {
               heart.setImage(new Image("co/uk/artatawe/gui/Icons/icons8-love-50.png"));
            }
        }
        // Check watchers in database

        String sqlSelectWatching = "select * from user,watching where user.username = watching.username " +
                " and auctionid = '" + artwork.getArtworkID() + "';";
        for (User user : userDatabaseManager.getAllUsers(sqlSelectWatching)) {
            if (isWatching(user)) {
                watchIcon.setImage(new Image("co/uk/artatawe/gui/Icons/full-eye1.png"));
            }
        }

    }


    /**
     * Get the username.
     *
     * @return username of logged in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username of logged in user.
     *
     * @param username username of logged in user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get file location for the auction's photo.
     *
     * @return file location of photo.
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Set the location for the auction's photo.
     *
     * @param photo file location of photo.
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Triggers when user makes bid.
     * Validates bid.
     * Checks number of bids left for auction, if 1 then latest bidder won.
     * Updates information to database.
     * @param event
     */
    @FXML
    void handleButtonAction(ActionEvent event) {
        errorMessage.setVisible(false);
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
                successLabel.setText("Successful.");
                successLabel.setTextFill(Paint.valueOf("GREEN"));
            //Complete auction.
            } else {
                //Get id of winning bid.
                String sqlSelectBid = "SELECT * FROM BID WHERE bidamount = " + bidDatabaseManager.getMaxBid(artwork.getArtworkID()); //get highest bid for this auction.
                Bid bid = bidDatabaseManager.getBid(sqlSelectBid);

                //Update auction info.
                sqlUpdateBidAmount = "Update auction set numofbidsleft = " + (auction.getNumOfBidsLeft() - 1) + ", auctioncomp = 1, " +
                        "winningBid = " + bid.getBidID() + " where auctionid = " + this.artwork.getArtworkID() +
                        ";";
                makeBidButton.setDisable(true);
                successLabel.setText("You have won the auction!");
                successLabel.setTextFill(Paint.valueOf("#035d67"));
            }

            //Update num of bids left in GUI.
            numOfBidLeft.setText(Integer.toString(auction.getNumOfBidsLeft() - 1));
            reservedPrice.setText(bid.getText());
            auctionDatabaseManager.updateStatement(sqlUpdateBidAmount);
        }
    }

    /**
     * Validates bid.
     * If it is a double and greater than the highest bid.
     *
     * @return true if bid is valid.
     */
    public boolean valMakeBid() {
        try {
            Double.parseDouble(bid.getText());
            if (Double.parseDouble(bid.getText()) > Double.parseDouble(reservedPrice.getText())) {
                return true;
            } else {
                errorMessage.setVisible(true);
                errorMessage.setText("'Bid is too low'");
                errorMessage.setTextFill(Paint.valueOf("RED"));
                return false;
            }

        } catch (NumberFormatException ex) {
            errorMessage.setVisible(true);
            errorMessage.setText("'Enter digits only'");
            errorMessage.setTextFill(Paint.valueOf("RED"));
        }
        return false;


    }


    /**
     * Triggers when user presses the heart button.
     * Checks to see if seller is already a favourite.
     * If seller already a favourite, removes seller from favourite.
     * Else adds to favourite.
     * @param event event.
     */
    @FXML
    void handleHeartAction(ActionEvent event) {
        FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

        String sqlSelect = "Select * from user where username = '" + this.sellerName.getText() + "';";
        for (User a: userDatabaseManager.getAllUsers(sqlSelect)) {
            if (isFavouriteOf(a)) {
                String sqlDelete = "delete from favouriteuser where username1 = '" + this.username
                        + "' and username2 = '" + this.sellerName.getText() + "';";

                favouriteUserDatabaseManager.executeStatement(sqlDelete);

                heart.setImage(new Image("co/uk/artatawe/gui/Icons/icons8-heart-48.png"));
            } else {
                String sqlInsert = "insert into favouriteuser(username1,username2) values ('" +
                        this.username + "', '" + this.sellerName.getText() + "');";

                favouriteUserDatabaseManager.executeStatement(sqlInsert);

                heart.setImage(new Image(("co/uk/artatawe/gui/Icons/icons8-love-50.png")));
            }

        }
    }


    /**
     * Triggers when user presses watch icon.
     * If user is already watching auction, removes from watchers and decreases num of people watching.
     * Else adds user to the watchers list. Increases num of people watching.
     * @param event event.
     */
    @FXML
    void watchAction(ActionEvent event) {
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
        WatchingDatabaseManager watchingDatabaseManager = new WatchingDatabaseManager();
        String sqlSelectWatch = "Select * from watching where username = '" + this.username + "' and auctionid = " + this.auction.getArtwork().getArtworkID() + ";";
        ArrayList<Watching> numOfWatching = new WatchingDatabaseManager().getAllWatching(sqlSelectWatch);


        if (numOfWatching.size() > 0) {
            String sqlDelete = "delete from watching where auctionid = '" + this.auction.getArtwork().getArtworkID()
                    + "' and username = '" + this.username + "';";

            watchIcon.setImage(new Image("co/uk/artatawe/gui/Icons/icons8-eye-40.png"));
            watchingDatabaseManager.executeStatement(sqlDelete);
            numPeopleWatching--;
            numWatchers.setText(Integer.toString(numPeopleWatching));
        } else {
            String sqlInsert = "insert into watching values (" + this.auction.getArtwork().getArtworkID() + ",'" + this.username + "');";

            watchingDatabaseManager.executeStatement(sqlInsert);
            watchIcon.setImage(new Image("co/uk/artatawe/gui/Icons/full-eye1.png"));
            numPeopleWatching++;
            numWatchers.setText(Integer.toString(numPeopleWatching));
        }

    }
}
