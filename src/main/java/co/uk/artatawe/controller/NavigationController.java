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
 * Controller class for the navigation.
 *
 * @author Tihomir Trendafilov
 */
public class NavigationController implements Initializable {

    private String username; //logged in user.
    

    @FXML
    private Pane centerPane; //a pane in the center of the main pane


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

    /**
     * When loading the scene loads browse auction scene in the center of the page.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    	//creates a new controller
        BrowseAuctionController browseAuctionController = new BrowseAuctionController();
        browseAuctionController.setUsername(this.username);

    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseAuctions.fxml"));
    	 
    	 //sets the controller manually
    	 fxmlLoader.setController(browseAuctionController);
         try {
             centerPane.getChildren().add(fxmlLoader.load()); //sets the center of the pane to browse auction scene
			
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
    	
    	//creates a new controller
        ProfilePageController profilePageController = new ProfilePageController();
        profilePageController.setUsername(this.username);
        profilePageController.setRootPane(centerPane);
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/ProfilePage.fxml"));
        //sets the controller manually
        fxmlLoader.setController(profilePageController);
        centerPane.getChildren().add(fxmlLoader.load()); //sets the center of the pane to Profile Page scene
      
    }

    /**
     * Displays Browse Auctions page when clicked.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleAuctionAction(ActionEvent event) throws IOException {
    
    	
    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseAuctions.fxml"));
    	//creates a new controller
    	 BrowseAuctionController browseAuctionController = new BrowseAuctionController();
    	 browseAuctionController.setUsername(this.username);
    	//sets the controller manually
    	 fxmlLoader.setController(browseAuctionController);

    	//sets the center of the pane to browse auction scene
          centerPane.getChildren().add(fxmlLoader.load());
    }
    
    /**
     * Displays Browse Users page when clicked.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleUsersAction(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseUsers.fxml"));
    	//creates a new controller
    	BrowseUsersController browseUsersController = new BrowseUsersController();
    	browseUsersController.setUsername(this.username);
    	fxmlLoader.setController(browseUsersController); //sets the controller manually

    	//sets the center of the pane to browse Users scene
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
    /**
     * Displays Create Auction page when clicked.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleCreateAuctionAction(ActionEvent event) throws IOException {
    	//creates a new controller
        CreateAuctionController createAuctionController = new CreateAuctionController();
        createAuctionController.setUsername(this.username);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CreateAuction.fxml"));

    	fxmlLoader.setController(createAuctionController); //sets the controller manually


    	//sets the center of the pane to Create Auction scene
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
    /**
     * Displays Current Auctions page when clicked which is page of auctions that are currently auctioned by the user.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleMyAuctionsAction(ActionEvent event) throws IOException {
    	//creates a new controller
        CurrentAuctionController currentAuctionController = new CurrentAuctionController();
        currentAuctionController.setUsername(this.username);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CurrentAuctions.fxml"));

    	fxmlLoader.setController(currentAuctionController); //sets the controller manually

    	//sets the center of the pane to Current Auctions scene
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
    /**
     * Displays Bid History page when clicked.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleBidAction(ActionEvent event) throws IOException {
    	//creates a new controller
        BidHistoryController bidHistoryController = new BidHistoryController();
        bidHistoryController.setUsername(this.username);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BidHistory.fxml"));

    	fxmlLoader.setController(bidHistoryController); //sets the controller manually

    	//sets the center of the pane to Bid History scene
        centerPane.getChildren().add(fxmlLoader.load());
    	
    }
    
    /**
     * Displays Log in page when clicked.
     * @param event event.
     * @throws IOException 
     */
    @FXML
    void handleLogOutAction(ActionEvent event) throws IOException {
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
      * Returns the username of the user that is log in.
      * @return username
      */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the user that is log in.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

        
}
