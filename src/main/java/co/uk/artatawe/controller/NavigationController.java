package co.uk.artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.database.ArtworkDatabaseManager;

/**
 * Controller class for browse auction.
 *
 * @author 908928
 * @author Plamena Tseneva
 */
public class NavigationController implements Initializable {

    private String username; //logged in user.
    
    
    private final int WIDTH = 800; //size of window.
    private final int HEIGHT = 600; //size of window.

    @FXML
    private BorderPane centerPane;


    /**
     * Empty constructor.
     */
    public NavigationController() {
    }

    /**
     * Sets username.
     * @param username username of logged in user.
     */
    public NavigationController(String username) {
        this.username = username;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BrowseAuctionController browseAuctionController = new BrowseAuctionController();
        browseAuctionController.setUsername(this.username);

    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseAuctions.fxml"));

    	 fxmlLoader.setController(browseAuctionController);
         try {
			centerPane.setCenter(fxmlLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    	 
    	
       
    }




    /**
     * Displays profile page when clicked.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleProfileAction(ActionEvent event) throws IOException {
    	
   	 
    	//for some reason it needs to have an existing scene in order to pass the username to it and then initialise the labels
    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/ProfilePage.fxml"));
         
         centerPane.setCenter(fxmlLoader.load());
    	 
      
       	//manually set controller.
         ProfilePageController profilePageController = new ProfilePageController();
         profilePageController.setUsername(this.username);
         fxmlLoader.setController(profilePageController);
	
       	 
    	 FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/ProfilePage.fxml"));
         
         centerPane.setCenter(fxmlLoader1.load());
    	 
      
       	//manually set controller.
         ProfilePageController profilePageController1 = new ProfilePageController();
         profilePageController1.setUsername(this.username);
         fxmlLoader.setController(profilePageController1);
      

        
         
         
         
    }

    /**
     * Displays create auction when clicked.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleAuctionAction(ActionEvent event) throws IOException {
    
    	
    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseAuctions.fxml"));

    	 BrowseAuctionController browseAuctionController = new BrowseAuctionController();
    	 browseAuctionController.setUsername(this.username);

    	 fxmlLoader.setController(browseAuctionController);

    	 centerPane.setCenter(fxmlLoader.load());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
        
}
