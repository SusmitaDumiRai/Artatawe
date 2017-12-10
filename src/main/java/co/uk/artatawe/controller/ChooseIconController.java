package co.uk.artatawe.controller;

/**
 * Handles choose icon fxml file.
 * Displays app avialbe icons and lets the user choose one for their profile.
 *
 * @author Tihomir Trendafilov
 */

import co.uk.artatawe.database.FavouriteUserDatabaseManager;
import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.main.FavouriteUsers;
import co.uk.artatawe.main.User;
import co.uk.artatawe.profileImage.SavedProfileImage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChooseIconController implements Initializable {

    private final int WIDTH_NAVIGATION = 800; //WIDTH size for navigation window.
    private final int HEIGHT_NAVIGATION = 600; //HEIGHT size for navigation window.
    private final int WIDTH_REGISTER = 590; //WIDTH size for REGISTER  window.
    private final int HEIGHT_REGISTER = 510; //HEIGHT for REGISTER  window.
    private final int IMAGE_WIDTH = 150;
    private final int GAP = 10;
    private String username; //logged in user.
    private int register = 1; //do you come from register page or not: 0 for yes 1 for no.
    private User user; //the user that is choosing an icon.

    @FXML
    private TilePane tilePane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button dogButton;

    @FXML
    private Button catButton;

    @FXML
    private Button dog2Button;

    @FXML
    private Button bearButton;

    @FXML
    private Button penguinButton;

    @FXML
    private Pane centerPane; //a pane in the center of the main pane.


    /**
     * Empty constructor.
     */
    public ChooseIconController() {

    }

    /**
     * Constructor with register
     *
     * @param register do you come from the register page(0) or not (1).
     */
    public ChooseIconController(int register) {

    }

    /**
     * Constructor with register and username
     *
     * @param register do you come from the register page(0) or not (1).
     * @param username username of logged in user.
     */
    public ChooseIconController(String username, int register) {
        this.register = register;
        this.username = username;
    }

    /**
     * Constructor with user so when a user chooses an icon the change would be saved in the data base.
     *
     * @param user the user that is choosing an icon.
     */
    public ChooseIconController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * It runs when you chose the first Icon.
     * It takes into account where you come from and returns you to that page while saving your choice of icon.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    public void chooseDog1Auction(ActionEvent event) throws IOException {
        if (register == 0) {
            String dog1Path = "co/uk/artatawe/profileImage/SavedProfileImages/PresetImage_Bear.jpg";
            openRegisterPage(event, dog1Path);

        } else {
            setProfileImage("PresetImage_Bear.jpg");
            goBackToProfilePage(event);

        }
    }

    /**
     * It runs when you chose the second Icon.
     * It takes into account where you come from and returns you to that page while saving your choice of icon.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    public void chooseCatAuction(ActionEvent event) throws IOException {
        if (register == 0) {
            String catPath = "co/uk/artatawe/profileImage/SavedProfileImages/PresetImage_Cat.jpg";
            openRegisterPage(event, catPath);

        } else {
            setProfileImage("PresetImage_Cat.jpg");
            goBackToProfilePage(event);

        }

    }

    /**
     * It runs when you chose the third Icon.
     * It takes into account where you come from and returns you to that page while saving your choice of icon.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    public void chooseDog2Auction(ActionEvent event) throws IOException {
        if (register == 0) {
            String dog2Path = "co/uk/artatawe/profileImage/SavedProfileImages/PresetImage_Dog.jpg";
            openRegisterPage(event, dog2Path);

        } else {
            setProfileImage("PresetImage_Dog.jpg");
            goBackToProfilePage(event);

        }

    }

    /**
     * It runs when you chose the forth Icon.
     * It takes into account where you come from and returns you to that page while saving your choice of icon.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    public void chooseBearAuction(ActionEvent event) throws IOException {
        if (register == 0) {
            String bearPath = "co/uk/artatawe/profileImage/SavedProfileImages/PresetImage_Lion.jpg";
            openRegisterPage(event, bearPath);

        } else {
            setProfileImage("PresetImage_Lion.jpg");
            goBackToProfilePage(event);

        }

    }

    /**
     * It runs when you chose the fifth Icon.
     * It takes into account where you come from and returns you to that page while saving your choice of icon.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    public void choosePenguinAuction(ActionEvent event) throws IOException {
        if (register == 0) {
            String penguinPath = "co/uk/artatawe/profileImage/SavedProfileImages/PresetImage_Penguin.jpg";
            openRegisterPage(event, penguinPath);

        } else {
            setProfileImage("PresetImage_Penguin.jpg");
            goBackToProfilePage(event);

        }

    }

    /**
     * Set the profile image of user.
     *
     * @param picName name of picture.
     */
    private void setProfileImage(String picName) {
        SavedProfileImage s = new SavedProfileImage(
                "/co/uk/artatawe/profileImage/SavedProfileImages/"
                        + picName);
        user.setProfileImage(s);
        UserDatabaseManager u = new UserDatabaseManager();
        u.updateProfileImage(user,
                "/co/uk/artatawe/profileImage/SavedProfileImages/"
                        + picName);

    }

    /**
     * Opens up register page.
     *
     * @param event event.
     * @param path  path to profile image.
     */
    private void openRegisterPage(ActionEvent event, String path) {
        RegisterController registerController
                = new RegisterController();
        registerController.setAvatarImagePath(path);
        registerController.setRootPane(centerPane);

        FXMLLoader fxmlLoader
                = new FXMLLoader(getClass().getClassLoader().getResource(
                "co/uk/artatawe/gui/RegisterUser.fxml"));

        //Sets the controller manually.
        fxmlLoader.setController(registerController);
        //Puts the custom profile image page scene on the root pane.
        try {
            centerPane.getChildren().clear(); //clears the old scene
            centerPane.getChildren().add(fxmlLoader.load()); //set the center of the pane to show auction scene
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Returns to profile page.
     *
     * @param event event.
     */
    private void goBackToProfilePage(ActionEvent event) {
        //Creates a new controller.
        ProfilePageController profilePageController
                = new ProfilePageController();
        profilePageController.setUsername(username);
        profilePageController.setRootPane(centerPane);

        FXMLLoader fxmlLoader
                = new FXMLLoader(getClass().getClassLoader().getResource(
                "co/uk/artatawe/gui/ProfilePage.fxml"));

        //Sets the controller manually.
        fxmlLoader.setController(profilePageController);
        //Puts the custom profile image page scene on the root pane.
        try {
            centerPane.getChildren().clear(); //clears the old scene
            centerPane.getChildren().add(fxmlLoader.load()); //set the center of the pane to show auction scene
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Gets logged in user's username.
     *
     * @return username of logged in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets logged in user's username.
     *
     * @param username username of logged in user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets register param indicating where are you coming from.
     *
     * @return register.
     */
    public int getRegister() {
        return register;
    }

    /**
     * Sets register param indicating where are you coming from.
     *
     * @param register param indicating where are you coming from.
     */
    public void setRegister(int register) {
        this.register = register;
    }

}

