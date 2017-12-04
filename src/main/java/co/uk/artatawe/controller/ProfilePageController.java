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

    @FXML
    private Label username;


    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    public ProfilePageController() {
    }

    public ProfilePageController(String username) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    void handleProfileAction(Event event) {
        //TODO fill in information in this tiho.
    }


}
