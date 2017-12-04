package co.uk.artatawe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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
 */
public class BrowseAuctionController implements Initializable {

    private String username;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    
    public void getImages(){
    	
    	 ArrayList<Artwork> artworks = ArtworkDatabaseManager.getAllArtworks();
    	    int noOfArtworks = artworks.size();
    	    ArrayList<ImageView> images = new ArrayList<>();
    	    StackPane root = new StackPane();
    	    
    	    for(Artwork a : artworks){
    	    	images.add(new ImageView(a.getPhoto()));
    	    }
    	    
    	    for(ImageView i : images){
    	    	root.getChildren().add(i);
    	    }
    }
    
    public BrowseAuctionController() {
    }

    public BrowseAuctionController(String username) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Displays profile page when clicked.
     * @param event
     */
    @FXML
    void handleProfileAction(ActionEvent event) {

        System.out.println("My Profile clicked!");
    }

    @FXML
    void handleAuctionAction(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/CreateAuction.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new auction");
            stage.setScene(new Scene(root, WIDTH, HEIGHT));

            CreateAuctionController createAuctionController = fxmlLoader.getController();

            createAuctionController.changeSellerUsername(this.username);

            stage.show(); //display create auctions.

            //hides current window.
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }



}
