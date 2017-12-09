
package co.uk.artatawe.controller;

/**
 * Controller for register page.
 *
 * @author Tihomir Trendafilov
 *
 * @version 1.0
 */
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

public class RegisterController implements Initializable {
	
	private final int WIDTH_NAVIGATION = 800; //WIDTH size for navigation window.
    private final int HEIGHT_NAVIGATION = 600; //HEIGHT size for navigation window

    //private final String ERROR = "Wrong username";
	
	
    
    
    
    
    
    
    
     @FXML
     private TextField username;
    
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
     * @param event
     * @throws IOException
     */
    @FXML
    void createAccount(ActionEvent event) {
    	createAccountAuction(event); 
    }
    
    private void createAccountAuction(ActionEvent event) {
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
    	
   
    


}