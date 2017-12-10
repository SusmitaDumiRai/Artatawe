package co.uk.artatawe.controller;

import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.AuctionDatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller class for create auction page.
 *
 * @author 908928
 */
public class CreateAuctionController implements Initializable {

    private String username; //logged in user
    private String extraImageFilePath; //file path for extra image.
    private String photoFilePath;

    @FXML
    private Button submitButton;

    @FXML
    private Button imageBtn;

    @FXML
    private Button imageExtraPhotoBtn;

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
    private Label sellerName; //seller username.

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
    private Label extraPhotoText;

    @FXML
    private Label materialText;

    @FXML
    private Label successLabel;

    @FXML
    private HBox extraPhotoHBox;

    @FXML
    private HBox photoHBox;

    private FileChooser fileChooser = new FileChooser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sellerName.setText(username);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")); //only allows png or jpg.
    }

    /**
     * Hide text boxes not related to painting.
     *
     * @param event event.
     */
    @FXML
    void handlePaintingRadioAction(ActionEvent event) {
        depth.setVisible(false);
        material.setVisible(false);
        imageExtraPhotoBtn.setVisible(false);
        depthX.setVisible(false);
        materialText.setVisible(false);
        extraPhotoText.setVisible(false);
    }

    /**
     * Display textboxes related to sculpture.
     *
     * @param event event.
     */
    @FXML
    void handleSculptureRadioAction(ActionEvent event) {
        depth.setVisible(true);
        material.setVisible(true);
        imageExtraPhotoBtn.setVisible(true);
        depthX.setVisible(true);
        materialText.setVisible(true);
        extraPhotoText.setVisible(true);
    }


    /**
     * Get file location of image.
     *
     * @param event event.
     */
    @FXML
    void handleImageButtonAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            imageBtn.setText(file.getAbsolutePath()); //gets absolute path. Images will no longer be relative.
        }
    }

    /**
     * Get file location of extra image.
     *
     * @param event event.
     */
    @FXML
    void handleImageExtraPhotoBtn(ActionEvent event) {
        Node node = (Node) event.getSource();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            imageBtn.setText(file.getAbsolutePath()); //gets absolute path. Images will no longer be relative.
        }
    }

    /**
     * Display current seller's name.
     *
     * @param username username of logged in user.
     */
    public void changeSellerUsername(String username) {
        sellerName.setText(username);
    }


    /**
     * Validates and create auction when pressing submit.
     *
     * @param event event.
     */
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
                    checkExtraPhoto();
                    insertIntoArtwork(false);
                    insertIntoAuction();
                }
            }

            successLabel.setText("Success!");
            successLabel.setTextFill(Paint.valueOf("GREEN"));


        }
    }

    /**
     * Clears all data entered.
     */
    public void clearAllData() {

        title.clear();
        year.clear();
        width.clear();
        height.clear();
        depth.clear();
        reservedPrice.clear();
        allowedBids.clear();
        description.clear();
        material.clear();

        imageBtn.setText("Select image");
        imageExtraPhotoBtn.setText("Select extra photo");
    }

    /**
     * Validates if one radio button is selected.
     *
     * @return true if one radio button selected.
     */
    public boolean valRadioBtnSelected() {
        if (paintingRadioButton.isSelected() || sculptureRadioButton.isSelected()) {
            return true;
        } else {
            radioButtonError.setTextFill(Paint.valueOf("RED"));
        }

        return false;
    }

    /**
     * Validates user entered value for title.
     *
     * @return true if not empty.
     */
    public boolean valTitle() {
        return !(title.getText().isEmpty()); //return false is empty.
    }


    /**
     * Validates user entered value for creator name.
     *
     * @return true if not empty.
     */
    public boolean valCreatorName() {
        return !(creatorName.getText().isEmpty()); //return false is empty.
    }

    /**
     * Validates user entered value for year.
     *
     * @return true if int.
     */
    public boolean valYear() {
        try {
            Integer.parseInt(year.getText());
            return true;
        } catch (NumberFormatException ex) {
            yearError.setTextFill(Paint.valueOf("RED"));
        }
        return false;
    }


    /**
     * Validates user entered double value for width.
     *
     * @return true if double.
     */
    public boolean valWidth() {
        try {
            Double.parseDouble(width.getText());
            return true;
        } catch (NumberFormatException ex) {
            sizeError.setTextFill(Paint.valueOf("RED"));
        }
        return false;
    }

    /**
     * Validates user entered double value for height.
     *
     * @return true if double.
     */
    public boolean valHeight() {
        try {
            Double.parseDouble(height.getText());
            return true;
        } catch (NumberFormatException ex) {
            sizeError.setText("Invalid height");
        }
        return false;
    }

    /**
     * Validates user entered double value for depth.
     *
     * @return true if double.
     */
    public boolean valDepth() {
        try {
            Double.parseDouble(depth.getText());
            return true;
        } catch (NumberFormatException ex) {
            sizeError.setText("Invalid depth");
        }
        return false;
    }

    /**
     * Validates user entered double value for reserved price
     * and is greater than 0.
     *
     * @return true if validation passes..
     */
    public boolean valReservedPrice() {
        try {
            Double.parseDouble(reservedPrice.getText());
            if (Double.parseDouble(reservedPrice.getText()) > 0) {
                return true;
            }
        } catch (NumberFormatException ex) {
            priceError.setTextFill(Paint.valueOf("RED"));
        }
        return false;
    }

    /**
     * Validates user entered value for year.
     *
     * @return true if int.
     */
    public boolean valAllowedBids() {
        try {
            Integer.parseInt(allowedBids.getText());
            if (Integer.parseInt(allowedBids.getText()) > 0) {
                return true;
            } else {
                allowedBidsError.setTextFill(Paint.valueOf("RED"));
                return false;
            }

        } catch (NumberFormatException ex) {
            allowedBidsError.setText("Please enter a number");

        }
        return false;
    }

    /**
     * Validate to see if user selected a photo.
     * If it's not empty, create file path.
     * @return true of user selected a photo.
     */
    public boolean valPhoto() {
        if (!(imageBtn.getText().equals("Select image"))) { //check not standard message.
            photoFilePath = "file:///" + imageBtn.getText().replaceAll("'", "''"); //replace all apostrophes.
            photoFilePath = photoFilePath.replaceAll("\\\\", "\\\\\\\\");
            return true;
        }
        return false;
    }

    /**
     * If extra photo not selected, keep it empty.
     * Else create file path.
     */
    public void checkExtraPhoto() {
        if (imageExtraPhotoBtn.getText().equals("Select extra photo")) {
            extraImageFilePath = "";
        } else {
            extraImageFilePath = "file:///" + imageExtraPhotoBtn.getText().replaceAll("'", "''");
            extraImageFilePath = extraImageFilePath.replaceAll("\\\\", "\\\\\\\\");
        }
    }

    /**
     * Insert artwork into artworks table.
     *
     * @param isPainting true if artwork is painting.
     */
    public void insertIntoArtwork(boolean isPainting) {
        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String sqlInsertArtwork;

        if (isPainting) {
            sqlInsertArtwork = "INSERT INTO ARTWORK (title, description, photo, nameofcreator, reservedprice, dateentered," +
                    "bidsallowed, typeofartwork, width, height) values ('" + title.getText().replaceAll("'", "''") + "', '" +
                    description.getText().replaceAll("'", "''") + "'," +
                    "'" + photoFilePath + "'," + "'" + creatorName.getText().replaceAll("'", "''") + "'," +
                    reservedPrice.getText() + "," +
                    "'" + dateFormat.format(date) + "','" + allowedBids.getText() + "','painting', '" + width.getText() + "','" +
                    height.getText() + "');";


        } else {

            sqlInsertArtwork = "INSERT INTO ARTWORK (title, description, photo, nameofcreator, reservedprice, dateentered," +
                    "bidsallowed, typeofartwork, width, height, depth, mainmaterial, extraphotos) values ('" + title.getText().replaceAll("'", "''") + "', '" +
                    description.getText().replaceAll("'", "''") +
                    "'," + "'" + photoFilePath + "'," + "'" + creatorName.getText().replaceAll("'", "''") + "'," + reservedPrice.getText() + "," +
                    "'" + dateFormat.format(date) + "','" + allowedBids.getText() + "','sculpture', '" + width.getText() + "','" + height.getText() + "','" +
                    depth.getText() + "','" + material.getText().replaceAll("'", "''") + "','" + extraImageFilePath + "');";

        }

        artworkDatabaseManager.updateStatement(sqlInsertArtwork);
    }

    /**
     * Puts up artwork up for auction.
     * Adds data into auction table.
     */
    public void insertIntoAuction() {
        AuctionDatabaseManager auctionDatabaseManager = new AuctionDatabaseManager();
        ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();

        String sqlInsertAuction = "INSERT INTO AUCTION (auctionid, seller,  numofbidsleft, auctioncomp, highestbid) values ('" +
                artworkDatabaseManager.getArtworkID(title.getText()) +
                "','" + sellerName.getText() + "','" + allowedBids.getText() + "','0','" + reservedPrice.getText() + "');";

        auctionDatabaseManager.updateStatement(sqlInsertAuction);
    }

    /**
     * Get username.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.
     *
     * @param username userame.
     */
    public void setUsername(String username) {
        this.username = username;
    }


}
