package co.uk.artatawe.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class created for profile page.
 *
 * @author 908928
 */
public class ProfilePageController implements Initializable {

    private String username;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    public ProfilePageController(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void handleProfileAction(Event event) {
        //TODO fill in information in this tiho.
    }


}
