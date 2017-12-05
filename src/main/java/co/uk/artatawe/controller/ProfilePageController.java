package co.uk.artatawe.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.sample.User;

/**
 * Controller class created for profile page.
 *
 * @author 908928
 */
public class ProfilePageController implements Initializable {

    private String enteredUserName; //username of person logged in.

    @FXML
    private Label userName;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label telephoneNumber;
    @FXML
    private Label address;


    public ProfilePageController() {
    }

    public ProfilePageController(String username) {
        this.enteredUserName = username;

    }

    /**
     * Loads information about the user that is log in.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

    	
    }

    public void setUserName(String userName) {
    	enteredUserName = userName;
    }


}
