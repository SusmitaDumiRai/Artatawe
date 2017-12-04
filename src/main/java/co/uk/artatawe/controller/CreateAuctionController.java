package co.uk.artatawe.controller;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.AuctionDatabaseManager;
import co.uk.artatawe.sample.Auction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller class for create auction page.
 * @author 908928
 */
public class CreateAuctionController  implements Initializable {

    @FXML
    private Label sellerName; //seller username.

    @FXML
    private Button submitButton;

    @FXML
    private RadioButton paintingRadioButton;

    @FXML
    private RadioButton sculptureRadioButton;

    @FXML
    private TextField title; //artwork title.

    @FXML
    private TextField creatorName;

    @FXML
    private TextField year; //year created by creator.


    @FXML
    private TextField width;

    @FXML
    private TextField height;

    @FXML
    private TextField depth;

    @FXML
    private TextField reservedPrice;

    @FXML
    private TextField allowedBids;

    @FXML
    private TextArea description;

    @FXML
    private TextField material;


    /**
     * Display current seller's name.
     * @param username username of logged in user.
     */
    public void changeSellerUsername(String username) {
        sellerName.setText(username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void handleSubmitAction(ActionEvent event) {
        insertIntoAuction();
    }

    /**
     * Creates the auction.
     * Commits information to artworks and auction database.
     */
    public void createAuction() {
        //validate all user inputs.
        if (valRadioBtnSelected() && valTitle() && valCreatorName() && valYear() &&
                valAllowedBids() && valWidth() && valHeight()) { //validate user responses.

            //Add to artwork and auction tables.
            if (paintingRadioButton.isSelected()) { //painting selected.
                insertIntoArtwork(true);
                insertIntoAuction();
            } else { //sculpture selected.
                if (valDepth()) {
                    insertIntoArtwork(false);
                    insertIntoAuction();
                }
            }
        }


    }

    /**
     * Validates if one radio button is selected.
     * @return true if one radio button selected.
     */
    public boolean valRadioBtnSelected() {
        if (paintingRadioButton.isSelected() || sculptureRadioButton.isSelected()) {
            return true;
        } else {
            System.out.println("choose one radio button");
            //TODO NICE ERROR MSG.
        }

        return false;
    }

    /**
     * Validates user entered value for title.
     * @return true if not empty.
     */
    public boolean valTitle() {
        return !(title.getText().isEmpty()); //return false is empty.
    }


    /**
     * Validates user entered value for creator name.
     * @return true if not empty.
     */
    public boolean valCreatorName() {
        return !(creatorName.getText().isEmpty()); //return false is empty.
    }

    /**
     * Validates user entered value for year.
     * @return true if int.
     */
    public boolean valYear() {
        try {
            Integer.parseInt(year.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter a number."); //needs to be changed.
            //TODO nice error message.
        }
        return false;
    }


    /**
     * Validates user entered double value for width.
     * @return true if double.
     */
    public boolean valWidth() {
        try {
            Float.parseFloat(width.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only plz"); // needs to be cahnged.
            //TODO display error message
        }
        return false;
    }

    /**
     * Validates user entered double value for height.
     * @return true if double.
     */
    public boolean valHeight() {
        try {
            Float.parseFloat(height.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only plz"); // needs to be cahnged.
            //TODO display error message
        }
        return  false;
    }

    /**
     * Validates user entered double value for depth.
     * @return true if double.
     * */
    public boolean valDepth() {
        try {
            Float.parseFloat(depth.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only plz"); // needs to be cahnged.
            //TODO display error message
        }
        return false;
    }

    /**
     * Validates user entered double value for reserved price.
     * @return true if double.
     */
    public boolean valReservedPrice() {
        try {
            Float.parseFloat(reservedPrice.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only plz"); // needs to be cahnged.
            //TODO display error message
        }
        return false;
    }

    /**
     * Validates user entered value for year.
     * @return true if int.
     */
    public boolean valAllowedBids() {
        try {
            Integer.parseInt(allowedBids.getText());
            if ( Integer.parseInt(allowedBids.getText()) > 0) {
                return true;
            } else {
                System.out.println("enter a number more than 0 plz.");
                return false;
                //TODO nice error message.
            }

        } catch (NumberFormatException ex) {
            System.out.println("enter a number."); //needs to be changed.
            //TODO nice error message.
        }
        return false;
    }

    /**
     * Insert artwork into artworks table.
     * @param isPainting true if artwork is painting.
     */
    public void insertIntoArtwork(boolean isPainting) {
        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String sqlInsertArtwork;

        if (isPainting) {
             sqlInsertArtwork =  "INSERT INTO ARTWORK (title, description, photo, nameofcreator, reservedprice, date entered," +
                    "bidsallowed, typeofartwork, width, height) values ('" + title.getText() + "', '" + description.getText() + "'," +
                    "'SOME PHOTO URL'," + "'" + creatorName.getText() + "'," + //TODO PHOTO URL
                    "'" + dateFormat.format(date) + "','" + allowedBids.getText() + "','painting', '" + width.getText() + "','" + height.getText() + "')";

        } else {
            sqlInsertArtwork = "INSERT INTO ARTWORK (title, description, photo, nameofcreator, reservedprice, date entered," +
                    "bidsallowed, typeofartwork, width, height, depth, mainmaterial, extraphotos) values ('" + title.getText() + "', '" + description.getText() + "'," +
                    "'SOME PHOTO URL'," + "'" + creatorName.getText() + "'," + //TODO PHOTO URL
                    "'" + dateFormat.format(date) + "','" + allowedBids.getText() + "','sculpture', '" + width.getText() + "','" + height.getText() + "','" +
                    depth.getText() + "','" + material.getText() + "','EXTRA PHOTOS URL');";
        }


        //artworkDatabaseManager.executeStatement(sqlInsertArtwork);VERY DANGEROUS LINE OF CODE. ONLY UNCOMMENT WHEN FINALISED.

    }

    /**
     * Puts up artwork up for auction.
     * Adds data into auction table.
     */
    public void insertIntoAuction() {
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();
        ArtworkDatabaseManager artworkDatabaseManager =  new ArtworkDatabaseManager();

        String sqlInsertAuction = "INSERT INTO AUCTION (auctionid, seller,  numofbidsleft, auctioncomp, highestbid) values ('" +  artworkDatabaseManager.getArtworkID(title.getText()) +
                "','"  +  sellerName.getText() + "','" + allowedBids.getText()  + "','0','" +  reservedPrice.getText() + "';";

        //auctionDatabaseManager.executeStatement(sqlInsertAuction); VERY DANGEROUS LINE OF CODE. ONLY UNCOMMENT WHEN FINALISED.

    }


}
