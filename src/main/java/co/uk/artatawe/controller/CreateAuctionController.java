package co.uk.artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for create auction page.
 * @author 908928
 */
public class CreateAuctionController  implements Initializable {

    @FXML
    private Label sellerName;

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
        valWidth();
        valDepth();
        valHeight();
    }

    public void createAuction() {

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


}
