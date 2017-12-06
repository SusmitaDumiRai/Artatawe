package co.uk.artatawe.controller;

import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.AuctionDatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;

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

    @FXML
    private TextField photo;

    @FXML
    private TextField extraPhoto;

    @FXML
    private Label radioButtonError;

    @FXML
    private Label priceError;

    @FXML
    private Label allowedBidsError;

    @FXML
    private Label sizeError;

    @FXML
    private Label yearError;

    @FXML
    private Label depthX;

    @FXML
    private Label materialText;

    @FXML
    private Label extraPhotoText;
    /**
     * Hide textboxes not related to painting.
     * @param event
     */
    @FXML
    void handlePaintingRadioAction(ActionEvent event) {
        depth.setVisible(false);
        material.setVisible(false);
        extraPhoto.setVisible(false);
        depthX.setVisible(false);
        materialText.setVisible(false);
        extraPhotoText.setVisible(false);
    }

    /**
     * Display textboxes related to sculpture.
     * @param event
     */
    @FXML
    void handleSculptureRadioAction(ActionEvent event) {
        depth.setVisible(true);
        material.setVisible(true);
        extraPhoto.setVisible(true);
        depthX.setVisible(true);
        materialText.setVisible(true);
        extraPhotoText.setVisible(true);
    }

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

        createAuction();
    }

    /**
     * Creates the auction.
     * Commits information to artworks and auction database.
     */
    public void createAuction() {
        //validate all user inputs.
        if (valRadioBtnSelected() && valTitle() && valCreatorName() && valYear() &&
                valAllowedBids() && valWidth() && valHeight() && valPhoto()) { //validate user responses.

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
            radioButtonError.setTextFill(Paint.valueOf("RED"));
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
            yearError.setTextFill(Paint.valueOf("RED"));
        }
        return false;
    }


    /**
     * Validates user entered double value for width.
     * @return true if double.
     */
    public boolean valWidth() {
        try {
            Double.parseDouble(width.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only plz"); // needs to be cahnged.
            //TODO display error message
            sizeError.setTextFill(Paint.valueOf("RED"));
        }
        return false;
    }

    /**
     * Validates user entered double value for height.
     * @return true if double.
     */
    public boolean valHeight() {
        try {
            Double.parseDouble(height.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only plz"); // needs to be cahnged.
            //TODO display error message
            sizeError.setText("Invalid height");
        }
        return  false;
    }

    /**
     * Validates user entered double value for depth.
     * @return true if double.
     * */
    public boolean valDepth() {
        try {
            Double.parseDouble(depth.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only plz"); // needs to be cahnged.
            //TODO display error message
            sizeError.setText("Invalid depth");
        }
        return false;
    }

    /**
     * Validates user entered double value for reserved price.
     * @return true if double.
     */
    public boolean valReservedPrice() {
        try {
            Double.parseDouble(reservedPrice.getText());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("enter digits only please"); // needs to be cahnged.
            //TODO display error message
            priceError.setTextFill(Paint.valueOf("RED"));
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
            if (Integer.parseInt(allowedBids.getText()) > 0) {
                return true;
            } else {
                System.out.println("enter a number more than 0 plz.");
                allowedBidsError.setTextFill(Paint.valueOf("RED"));
                return false;
                //TODO nice error message.


            }

        } catch (NumberFormatException ex) {
            System.out.println("enter a number."); //needs to be changed.
            //TODO nice error message.
            allowedBidsError.setText("Please enter a number");

        }
        return false;
    }

    public boolean valPhoto() {
        return !(photo.getText().isEmpty()); //return false is empty.
    }
    /**
     * Insert artwork into artworks table.
     * @param isPainting true if artwork is painting.
     */
    public void insertIntoArtwork(boolean isPainting) {
        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String sqlInsertArtwork;

        if (isPainting) {
             sqlInsertArtwork =  "INSERT INTO ARTWORK (title, description, photo, nameofcreator, reservedprice, dateentered," +
                    "bidsallowed, typeofartwork, width, height) values ('" + title.getText() + "', '" + description.getText() + "'," +
                    "'" + photo.getText() + "'," + "'" + creatorName.getText() + "'," +  reservedPrice.getText() + "," +
                    "'" + dateFormat.format(date) + "','" + allowedBids.getText() + "','painting', '" + width.getText() + "','" + height.getText() + "');";

        } else {
            sqlInsertArtwork = "INSERT INTO ARTWORK (title, description, photo, nameofcreator, reservedprice, dateentered," +
                    "bidsallowed, typeofartwork, width, height, depth, mainmaterial, extraphotos) values ('" + title.getText() + "', '" + description.getText() + "'," +
                    "'" +  photo.getText() + "'," + "'" + creatorName.getText() + "'," +  reservedPrice.getText() + "," +
                    "'" + dateFormat.format(date) + "','" + allowedBids.getText() + "','sculpture', '" + width.getText() + "','" + height.getText() + "','" +
                    depth.getText() + "','" + material.getText() + "','"  + extraPhoto.getText() + "');";
        }

        //System.out.println(sqlInsertArtwork);

        artworkDatabaseManager.executeStatement(sqlInsertArtwork); //VERY DANGEROUS LINE OF CODE. ONLY UNCOMMENT WHEN FINALISED.

    }

    /**
     * Puts up artwork up for auction.
     * Adds data into auction table.
     */
    public void insertIntoAuction() {
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();
        ArtworkDatabaseManager artworkDatabaseManager =  new ArtworkDatabaseManager();

        String sqlInsertAuction = "INSERT INTO AUCTION (auctionid, seller,  numofbidsleft, auctioncomp, highestbid) values ('" +  artworkDatabaseManager.getArtworkID(title.getText()) +
                "','"  +  sellerName.getText() + "','" + allowedBids.getText()  + "','0','" +  reservedPrice.getText() + "');";

        auctionDatabaseManager.executeStatement(sqlInsertAuction); // VERY DANGEROUS LINE OF CODE. ONLY UNCOMMENT WHEN FINALISED.

 //       System.out.println(sqlInsertAuction);
    }


}
