package co.uk.artatawe.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.sample.User;
import javafx.scene.layout.VBox;

/**
 * Controller class created for profile page.
 *
 * @author 908928
 */
public class ProfilePageController implements Initializable {

    private String username; //logged in user's username.

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

    @FXML
    private Label postcode;

    @FXML
    private ImageView avatar;

    @FXML
    private VBox avatarVbox;

    /**
     * Empty constructor.
     */
    public ProfilePageController() {

    }

    /**
     * Constructor that takes in username.
     * @param username username of logged in user.
     */
    public ProfilePageController(String username) {
        this.username = username;
    }


    /**
     * Loads information about the user that is log in.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUserInfo();

    }


    /**
     * Gets the logged in user's username.
     * @return logged in user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets logged in user's username.
     * @param username logged in user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets user info from database and displays it.
     */
    public void displayUserInfo() {
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

        User user = userDatabaseManager.getUser(this.username); //get user.

        userName.setText(this.username);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        telephoneNumber.setText(user.getPhoneNumber());
        address.setText(user.getAddress());
        postcode.setText(user.getPostcode());

        Image image = new Image(user.getProfileImage());
        avatar.setImage(image);
    }


}
