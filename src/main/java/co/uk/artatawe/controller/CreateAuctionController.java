package co.uk.artatawe.controller;

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
    private Label welcomeText;

    @FXML
    private Button sumbitButton;

    @FXML
    private Button myActions;

    @FXML
    private Button profile;

    @FXML
    private Button artwork;

    @FXML
    private Button bidHistory;

    @FXML
    private ToggleGroup typeArtwork;

    @FXML
    private RadioButton paintingRadioButton;

    @FXML
    private RadioButton sculptureRadioButton;

    @FXML
    private Button users;

    @FXML
    private Button auction;


    @FXML
    private TextField width;

    @FXML
    private TextField allowedBids;

    @FXML
    private TextField reservedPrice;

    @FXML
    private TextField title;

    @FXML
    private TextField height;

    @FXML
    private TextField creatorName;

    @FXML
    private TextField year;

    @FXML
    private TextArea description;






    public void changeSellerUsername(String username) {
        sellerName.setText(username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
