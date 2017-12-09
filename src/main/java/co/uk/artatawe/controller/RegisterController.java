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
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for register page.
 *
 * @author Tihomir Trendafilov
 *
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
	 private Button signInButton;
	 
	 @FXML
	 private Button createAccountButton;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {

	
    }
    
    /**
     * Returns you back to log in window.
     * @param event
     * @throws IOException
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
     * @param event
     * @throws IOException
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
    
    private void createAccountAuction(ActionEvent event) {
    	
    	//creating a new user and inserting into the system
    	UserDatabaseManager userDatabaseManager = new UserDatabaseManager();
    	
    	String sqlInsertUser = "INSERT INTO user (username, firstname,  surname, phonenumber, address, postcode, lastlogin, profileimage)  values ("
    			+ "'" + username.getText().replaceAll("'", "''") + "','"  + firstName.getText().replaceAll("'", "''") +
    			"','" + lastName.getText().replaceAll("'", "''")  + "','"  + telephoneNumber.getText() + "','"  +
    			address.getText().replaceAll("'", "''") + "','"  + postcode.getText().replaceAll("'", "''") +  "','"  +
    			"2017-02-20T09:12:13" +  "','"  + "co/uk/artatawe/profileImage/SavedProfileImages/PresetImage_Bear.jpg" +  "');";
    	
    	userDatabaseManager.executeStatement(sqlInsertUser);
    
    	Parent root;
        try {

        	//new controller so it can be set manually
            NavigationController navigationController = new NavigationController();
            navigationController.setUsername(username.getText());


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/Navigation.fxml"));
            //sets controller manually
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
     * @return true of username is valid.
     */
    public boolean validateUsername() {
        String usernameText = username.getText();
        
        if (usernameText.length() > 30 || usernameText.length() <= 0 || validateExistingUsers()) {
        	
        	return false;
        } else return true;
    }
    
    /**
     * Validates if the username entered is already taken.
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
     * @return true of first name is valid.
     */
    public boolean validateFirstName() {
        String firstNameText = firstName.getText();
        
        if (firstNameText.length() > 30 || firstNameText.length() <= 0) {
        	return false;
        } else return true;
    }
    
    /**
     * Validates last name. A last name can't be more than 30 characters.
     * @return true of last name is valid.
     */
    public boolean validateLastName() {
        String lastNameText = lastName.getText();
        
        if (lastNameText.length() > 30 || lastNameText.length() <= 0) {
        	return false;
        } else return true;
    }
    
    /**
     * Validates phone number. A phone number can't be more or less than 11 digits.
     * @return true of phone number is valid.
     */
    public boolean validatePhoneNumber() {
        String phoneNumText = telephoneNumber.getText();
        
        if (phoneNumText.length() > 11 || phoneNumText.length() < 11 || !isNumeric(phoneNumText)) {
        	return false;
        } else return true;
    }
    
    //checks if a string is made of numbers
    private boolean isNumeric(String str) {
      return str.matches(".*\\d+.*");
    }
    
    /**
     * Validates Address. An Address can't be more than 30 characters.
     * @return true of Address is valid.
     */
    public boolean validateAddess() {
        String addressText = address.getText();
        
        if (addressText.length() > 30 || addressText.length() <= 0) {
        	return false;
        } else return true;
    }
   
    /**
     * Validates postcode. An postcode can't be more than 6 characters.
     * @return true of postcode is valid.
     */
    public boolean validatePostcde() {
        String usernameText = postcode.getText();
        
        if (usernameText.length() > 6 || usernameText.length() <= 5) {
        	return false;
        } else return true;
    }
    


}