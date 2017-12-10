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
 *
 * @author 908928
 */
public class ShowAuctionController implements Initializable {

    private String username; //logged in user.
    private String photo; //selected artwork.
    private Artwork artwork;
    private Auction auction;
    private int numPeaopleWatching;

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

    private boolean isWatching(User user) {
        for (Watching watcher : getWatching()) {
            if (watcher.getUsername().equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that checks whether a seller is a favourite user of the current user.
     *
     * @param favouriteUsers favourite user object.
     * @return true if the seller is from the list of favourites of the loged in user.
     */
    private boolean isFavouriteOf(FavouriteUsers favouriteUsers) {
        if (favouriteUsers.getUser1().getUserName().equals(this.username)) {
            return true;
        }

        return false;
    }

    public void getNumOfPeopleWatching() {
        String sqlSelect = "Select * from watching where auctionid = " + this.auction.getArtwork().getArtworkID() + ";";
        ArrayList<Watching> watchings = new WatchingDatabaseManager().getAllWatching(sqlSelect);
        numPeaopleWatching = watchings.size();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xDepth.setVisible(false);
        depth.setVisible(false);
        material.setVisible(false);
        materialLabel.setVisible(false);

        getArtwork();
        getNumOfPeopleWatching();
        numWatchers.setText(Integer.toString(numPeaopleWatching));
    }

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

        //check whether the given seller is in user's favourites
        FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();
        String sqlSelect = "Select * from favouriteuser, auction where favouriteuser.username2 = '" + sellerName.getText() + "' " +
                "and favouriteuser.username1 =  '" + this.username + "';";
        heart.setImage(new Image(("co/uk/artatawe/gui/Icons/icons8-heart-48.png")));

        for (FavouriteUsers favs : favouriteUserDatabaseManager.getFavouriteUsers(sqlSelect)) {
            if (isFavouriteOf(favs)) {
                heart.setImage(new Image("co/uk/artatawe/gui/Icons/icons8-love-50.png"));
            }
        }


        //check wathchers in database
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
        String sqlSelectWatching = "select * from user,watching where user.username = watching.username " +
                " and auctionid = '" + artwork.getArtworkID() + "';";
        for (User user : userDatabaseManager.getAllUsers(sqlSelectWatching)) {
            if (isWatching(user)) {
                watchIcon.setImage(new Image("co/uk/artatawe/gui/Icons/full-eye.png"));
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
                //get id of winning bid.
                String sqlSelectBid = "SELECT * FROM BID WHERE bidamount = " + bidDatabaseManager.getMaxBid(artwork.getArtworkID()); //get highest bid for this auction.
                Bid bid = bidDatabaseManager.getBid(sqlSelectBid);

                //update auction info.
                sqlUpdateBidAmount = "Update auction set numofbidsleft = " + (auction.getNumOfBidsLeft() - 1) + ", auctioncomp = 1, " +
                        "winningBid = " + bid.getBidID() + " where auctionid = " + this.artwork.getArtworkID() +
                        ";";
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
     *
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
                return false;
            }

        } catch (NumberFormatException ex) {
            System.out.println("enter digits only please"); // needs to be cahnged.
            //TODO display error message
            errorMessage.setText("'Enter digits only'");
            errorMessage.setTextFill(Paint.valueOf("RED"));
        }
        return false;


    }


    @FXML
    void handleHeartAction(ActionEvent event) {
        FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();

        //String sql = "Select * from favouriteuser where username1 = '" + this.username + "' and username2 = '" + this.auction.getSeller().getUserName() + "';";

        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

        String sqlSelect = "Select * from user where username = '" + this.username + "';";
        for (User user : userDatabaseManager.getAllUsers(sqlSelect)) {

            BrowseUsersController browse = new BrowseUsersController();
        if (browse.isFavouriteOf(user)) {
            String sqlDelete = "delete from favouriteuser where username1 = '" + username
                    + "' and username2 = '" + sellerName.getText() + "';";

            favouriteUserDatabaseManager.executeStatement(sqlDelete);

            heart.setImage(new Image(("co/uk/artatawe/gui/Icons/icons8-heart-48.png")));
        } else {
            String sqlInsert = "insert into favouriteuser(username1,username2) values ('" +
                    username + "', '" + sellerName.getText() + "');";

            favouriteUserDatabaseManager.executeStatement(sqlInsert);

            heart.setImage(new Image(("co/uk/artatawe/gui/Icons/icons8-love-50.png")));
        }
    }

    }

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
            numPeaopleWatching--;
            numWatchers.setText(Integer.toString(numPeaopleWatching));
        } else {
            String sqlInsert = "insert into watching values (" + this.auction.getArtwork().getArtworkID() + ",'" + this.username + "');";

            watchingDatabaseManager.executeStatement(sqlInsert);
            watchIcon.setImage(new Image("co/uk/artatawe/gui/Icons/full-eye.png"));
            numPeaopleWatching++;
            numWatchers.setText(Integer.toString(numPeaopleWatching));
        }

    }
}
