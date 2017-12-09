package co.uk.artatawe.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.main.User;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * Controller class created for profile page.
 *
 * @author Tihomir Trendafilov
 */
public class ProfilePageController implements Initializable {

    private static String username; //logged in user's username.

    @FXML
    private BorderPane boarderPane;
    
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

    @FXML
    private MenuButton changeUserIcon;

    @FXML
    private MenuItem useAppIcons;

    @FXML
    private MenuItem useCustomIcon;

    private Pane rootPane;
    
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

	@FXML
    public void onChangeUserIconAction(ActionEvent event) throws IOException {
		if (changeUserIcon.getText() == useCustomIcon.getText()) {
	        CustomProfileImagePageController customProfileImagePageController = new CustomProfileImagePageController();

	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CustomProfileImagePage.fxml"));
	        
	        fxmlLoader.setController(customProfileImagePageController);
	        rootPane.getChildren().add(fxmlLoader.load());
		}
    }
	
	@FXML
    public void onUseCustomIconAction(ActionEvent event) throws IOException {
	        CustomProfileImagePageController customProfileImagePageController = new CustomProfileImagePageController();

	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CustomProfileImagePage.fxml"));
	        System.out.println(rootPane);
	        fxmlLoader.setController(customProfileImagePageController);
	        rootPane.getChildren().add(fxmlLoader.load());
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
        try{
        Image image = new Image(user.getProfileImage());
        avatar.setImage(image);
        } catch (Exception ex) {
            //do nothing so when username=null it doesnt crashes
        }
    }

	public void setRootPane(Pane rootPane) {
		this.rootPane = rootPane;
		
	}


}
