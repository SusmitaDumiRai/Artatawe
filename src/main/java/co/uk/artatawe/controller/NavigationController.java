package co.uk.artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private Pane centerPane;


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
             centerPane.getChildren().add(fxmlLoader.load());
			//centerPane.setCenter(fxmlLoader.load());
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
    	
    	
        ProfilePageController profilePageController = new ProfilePageController();
        profilePageController.setUsername(this.username);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/ProfilePage.fxml"));
        
        fxmlLoader.setController(profilePageController);
        centerPane.getChildren().add(fxmlLoader.load());
        //centerPane.setCenter(fxmlLoader.load());
      
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

    	 //centerPane.setCenter(fxmlLoader.load());
          centerPane.getChildren().add(fxmlLoader.load());
    }
    
    @FXML
    void handleUsersAction(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseUsers.fxml"));
    	
    	BrowseUsersController browseUsersController = new BrowseUsersController();
    	browseUsersController.setUsername(this.username);
    	fxmlLoader.setController(browseUsersController);

   	 	//centerPane.setCenter(fxmlLoader.load());
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
    @FXML
    void handleCreateAuctionAction(ActionEvent event) throws IOException {

        CreateAuctionController createAuctionController = new CreateAuctionController();
        createAuctionController.setUsername(this.username);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CreateAuction.fxml"));

    	fxmlLoader.setController(createAuctionController);

   	 	//centerPane.setCenter(fxmlLoader.load());
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
    @FXML
    void handleMyAuctionsAction(ActionEvent event) throws IOException {
        CurrentAuctionController currentAuctionController = new CurrentAuctionController();
        currentAuctionController.setUsername(this.username);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CurrentAuctions.fxml"));

    	fxmlLoader.setController(currentAuctionController);

   	 	//centerPane.setCenter(fxmlLoader.load());
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
    @FXML
    void handleBidAction(ActionEvent event) throws IOException {
        BidHistoryController bidHistoryController = new BidHistoryController();
        bidHistoryController.setUsername(this.username);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BidHistory.fxml"));

    	fxmlLoader.setController(bidHistoryController);

   	 	//centerPane.setCenter(fxmlLoader.load());
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
     
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

        
}
