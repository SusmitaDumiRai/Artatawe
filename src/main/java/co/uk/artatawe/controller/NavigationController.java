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

    private static String username; //logged in user.
    
    
    private final int WIDTH = 800; //size of window.
    private final int HEIGHT = 600; //size of window.

    @FXML
    private BorderPane centerPane;
    
    @FXML 
    private Label usernameLabel;


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
    	System.out.println(usernameLabel.getText());
    	
    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/NewBrowseAuctions.fxml"));

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
    	
   	 
    	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/NewProfilePage.fxml"));

         centerPane.setCenter(fxmlLoader.load()); 
    	 

       //manually set controller.
         NewProfilePageController newProfilePageController = new NewProfilePageController();
         newProfilePageController.setUsername(this.username);
         fxmlLoader.setController(newProfilePageController);


        
         newProfilePageController.setUsername(username); //parse username.
         
         
         
         
    }

    /**
     * Displays create auction when clicked.
     * @param event event.
     */
    @FXML
    void handleAuctionAction(ActionEvent event) {
        Parent root;
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CreateAuction.fxml"));
            root = fxmlLoader.load();

            stage.setTitle("Create new auction");
            //stage.setScene(new Scene(root, WIDTH, HEIGHT));
            //delete down
            stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
            stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

            CreateAuctionController createAuctionController = fxmlLoader.getController();

            createAuctionController.changeSellerUsername(this.username);

            stage.show(); //display create auctions.

            //hides current window.
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText(username);
    }
}
