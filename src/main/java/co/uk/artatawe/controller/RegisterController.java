package co.uk.artatawe.controller;

import co.uk.artatawe.database.UserDatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller for register page.
 *
 * @author Tihomir Trendafilov
 * @author 908928 - Susmita
 * @version 1.0
 */

public class RegisterController implements Initializable {

    private final int WIDTH_NAVIGATION = 800; //WIDTH size for navigation window.
    private final int HEIGHT_NAVIGATION = 600; //HEIGHT size for navigation window.


    @FXML
    private Label usernameErrorMessage;

    @FXML
    private Label firstNameErrorMessage;

    @FXML
    private Label lastNameErrorMessage;

    @FXML
    private Label phoneNumErrorMessage;

    @FXML
    private Label addressErrorMessage;

    @FXML
    private Label postcodeErrorMessage;

    @FXML
    private TextField username;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField telephoneNumber;

    @FXML
    private TextField address;

    @FXML
    private TextField postcode;

    @FXML
    private Button createAccountButton;

    @FXML
    private MenuItem customMenuItem;

    @FXML
    private Pane pane;

    @FXML
    private ImageView avatar;

    private String avatarPath = "co/uk/artatawe/profileImage/SavedProfileImages/PresetImage_Bear.jpg";

    private Pane rootPane; // the center of the scene


    /**
     * Empty Constructor.
     */
    public RegisterController() {

    }

    /**
     * Loads an image for profile avatar.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image newAvatar = new Image(avatarPath);
        avatar.setImage(newAvatar);
    }

    /**
     * Returns you back to log in window.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    void backToLogIn(ActionEvent event) throws IOException {
        //builds a new stage
        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Hides the old window
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

    /**
     * Creates an account with the details provided if they are valid and opens navigation window with your new account log on.
     * If there is a problem with the details displays error message indicating where the problem is.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    void createAccount(ActionEvent event) {

        //if any text field is invalid display error message under it.
        if (validateUsername()) {
            usernameErrorMessage.setTextFill(Paint.valueOf("transparent"));
        } else {
            usernameErrorMessage.setTextFill(Paint.valueOf("RED"));
        }

        if (validateFirstName()) {
            firstNameErrorMessage.setTextFill(Paint.valueOf("transparent"));
        } else {
            firstNameErrorMessage.setTextFill(Paint.valueOf("RED"));
        }

        if (validateLastName()) {
            lastNameErrorMessage.setTextFill(Paint.valueOf("transparent"));
        } else {
            lastNameErrorMessage.setTextFill(Paint.valueOf("RED"));
        }

        if (validatePhoneNumber()) {
            phoneNumErrorMessage.setTextFill(Paint.valueOf("transparent"));
        } else {
            phoneNumErrorMessage.setTextFill(Paint.valueOf("RED"));
        }

        if (validateAddess()) {
            addressErrorMessage.setTextFill(Paint.valueOf("transparent"));
        } else {
            addressErrorMessage.setTextFill(Paint.valueOf("RED"));
        }

        if (validatePostcde()) {
            postcodeErrorMessage.setTextFill(Paint.valueOf("transparent"));
        } else {
            postcodeErrorMessage.setTextFill(Paint.valueOf("RED"));
        }

        //if all text fields are valid create account.
        if (validateUsername() && validateFirstName() && validateLastName() && validatePhoneNumber()
                && validateAddess() && validatePostcde()) {
            createAccountAuction(event);
        }

    }

    /** Uses all the informatoin from the textfield and  the avatar that is being displayed and build account from it.
     *  Then logs the user into the system.
     *
     *  @param event event.
     */
    private void createAccountAuction(ActionEvent event) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //creating a new user and inserting into the system
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();


        String sqlInsertUser = "INSERT INTO user (username, firstname,  surname, phonenumber, address, postcode, lastlogin, profileimage)  values ("
                + "'" + username.getText().replaceAll("'", "''") + "','" + firstName.getText().replaceAll("'", "''") +
                "','" + lastName.getText().replaceAll("'", "''") + "','" + telephoneNumber.getText() + "','" +
                address.getText().replaceAll("'", "''") + "','" + postcode.getText().replaceAll("'", "''") + "','" +
                dateFormat.format(date) + "','" + avatarPath + "');";

        userDatabaseManager.executeStatement(sqlInsertUser);

        Parent root;
        try {

            //new controller so it can be set manually.
            NavigationController navigationController = new NavigationController();
            navigationController.setUsername(username.getText());


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/Navigation.fxml"));
            //sets controller manually.
            fxmlLoader.setController(navigationController);

            root = fxmlLoader.load();


            Stage stage = new Stage();
            stage.setTitle("Artatawe");
            stage.setScene(new Scene(root, WIDTH_NAVIGATION, HEIGHT_NAVIGATION));

            stage.show(); //display browse auctions.

            //hides current window.
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Validates username. A username can't be more than 30 characters.
     *
     * @return true of username is valid.
     */
    public boolean validateUsername() {
        String usernameText = username.getText();

        return !(usernameText.length() > 30 || usernameText.length() <= 0 || validateExistingUsers());
    }

    /**
     * Validates if the username entered is already taken.
     *
     * @return true of username is valid.
     */
    public boolean validateExistingUsers() {
        String usernameText = username.getText();
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

        if (!usernameText.isEmpty()) {
            for (String username1 : userDatabaseManager.getAllUsernames()) {
                if (usernameText.equals(username1)) {
                    return true;

                }
            }
        }
        return false;
    }

    /**
     * Validates first name. A first name can't be more than 30 characters.
     *
     * @return true of first name is valid.
     */
    public boolean validateFirstName() {
        String firstNameText = firstName.getText();

        return !(firstNameText.length() > 30 || firstNameText.length() <= 0);
    }

    /**
     * Validates last name. A last name can't be more than 30 characters.
     *
     * @return true of last name is valid.
     */
    public boolean validateLastName() {
        String lastNameText = lastName.getText();

        return !(lastNameText.length() > 30 || lastNameText.length() <= 0);
    }

    /**
     * Validates phone number. A phone number can't be more or less than 11 digits.
     *
     * @return true of phone number is valid.
     */
    public boolean validatePhoneNumber() {
        String phoneNumText = telephoneNumber.getText();

        return !(phoneNumText.length() > 11 || phoneNumText.length() < 11 || !isNumeric(phoneNumText));
    }

    /**
     * Checks if a string is made of numbers.
     *
     * @param str string to check.
     * @return true of numbers.
     */
    private boolean isNumeric(String str) {
        return str.matches(".*\\d+.*");
    }

    /**
     * Validates Address. An Address can't be more than 30 characters.
     *
     * @return true of Address is valid.
     */
    public boolean validateAddess() {
        String addressText = address.getText();

        return !(addressText.length() > 30 || addressText.length() <= 0);
    }

    /**
     * Validates postcode. An postcode can't be more than 6 characters.
     *
     * @return true of postcode is valid.
     */
    public boolean validatePostcde() {
        String usernameText = postcode.getText();
        if (usernameText.contains(" ")) {
        	return !(usernameText.length() > 8 || usernameText.length() <= 6);
        }
        return !(usernameText.length() > 7 || usernameText.length() <= 5);
    }

    /**
     * Opens custom drawing window. Lets the user draw an icon.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    void customDrawingAction(ActionEvent event) throws IOException {
        CustomProfileImagePageController customProfileImagePageController = new CustomProfileImagePageController(pane, true);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CustomProfileImagePage.fxml"));
        fxmlLoader.setController(customProfileImagePageController); //sets controller manually.

        try {
            pane.getChildren().add(fxmlLoader.load()); //changes the center of the page.
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Opens an window where the user can choose an icon from provided icons.
     *
     * @param event event.
     * @throws IOException io exception.
     */
    @FXML
    public void chooseAppIconAction(ActionEvent event) throws IOException {
        ChooseIconController chooseIconController
                = new ChooseIconController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/ChooseIcon.fxml"));
        chooseIconController.setRegister(0); //sets register so chooseIcon page knows you come from register.
        fxmlLoader.setController(chooseIconController); //sets controller manually.
        pane.getChildren().add(fxmlLoader.load()); //changes the center of the page.
    }

    /**
     * Setting the image the user is going to use as an profile icon.
     *
     * @param image the new profile image.
     */
    public void setIcon(Image image) {
        this.avatar.setImage(image);
    }

    /**
     * Sets the path of the image for user avatar.
     *
     * @param path of the image for user avatar.
     */
    public void setAvatarImagePath(String path) {
        this.avatarPath = path;
    }

    /**
     * Sets pane that is going to be the center of the scene.
     *
     * @param pane going to be the center of the scene.
     */
    public void setRootPane(Pane pane) {
        this.rootPane = pane;
    }

}