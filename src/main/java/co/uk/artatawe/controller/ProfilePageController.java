package co.uk.artatawe.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.database.ArtworkDatabaseManager;
import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.sample.Main;
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
    @FXML
    private Label postcode;
    @FXML
    private ImageView imv;

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
    	ArrayList<User> users = userDatabaseManager.getAllUsers();
    	for (int i=0; i<users.size(); i++){
    		if (users.get(i).equals(enteredUserName)) {
    			userName.setText(users.get(i).getUserName());
    	    	firstName.setText(users.get(i).getFirstName());
    	    	lastName.setText(users.get(i).getLastName());
    	    	telephoneNumber.setText(users.get(i).getPhoneNumber());
    	    	address.setText(users.get(i).getAddress());
    	    	postcode.setText(users.get(i).getPostcode());
    	    	//final Image image2 = new Image(Main.class.getResourceAsStream(users.get(i).getProfileImage()));
    	    	//imv.setImage(users.get(i).getProfileImage());
    	    	//users.get(i).getProfileImage().displayProfileImage(p);
    		}
    		else enteredUserName="ERROR!LOG IN USER IS NOT IN THE SYSTEM FILES";
    	}
    	
    	
    }

    public void setUserName(String userName){
    	enteredUserName=userName;
    }
    @FXML
    void handleProfileAction(Event event) {
        //TODO fill in information in this tiho.
    }


}
