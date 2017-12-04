package co.uk.artatawe.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for create auction page.
 * @author 908928
 */
public class CreateAuctionController  implements Initializable {

    public CreateAuctionController(String username) {
        changeSellerUsername(username);

    }

    @Override
    public String toString() {
        return "CreateAuctionController{" +
                "sellerName=" + sellerName.getText() +
                '}';
    }

    @FXML
    private Label sellerName;


    public void changeSellerUsername(String username) {
        sellerName.setText(username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
