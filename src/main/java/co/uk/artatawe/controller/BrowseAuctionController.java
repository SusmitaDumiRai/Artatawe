package co.uk.artatawe.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.artatawe.artwork.Artwork;
import co.uk.artatawe.artwork.Painting;
import co.uk.artatawe.artwork.Sculpture;
import co.uk.artatawe.database.ArtworkDatabaseManager;


/**
 * Controller class for browse auction.
 *
 * @author 908928
 * @author Plamena Tseneva
 */
public class BrowseAuctionController implements Initializable    {

    private String username; //logged in user.
    private final int WIDTH = 800; //size of window.
    private final int HEIGHT = 600; //size of window.
    private final int IMAGE_WIDTH = 200;
    private final int GAP = 10;
    
    private ArtworkDatabaseManager artworkDatabaseManager = new ArtworkDatabaseManager();
    
    private ObservableList<Artwork> observeArrayList;

    /*


    */

    @FXML
    private TilePane artworkTilePane;

    @FXML
    private ScrollPane artworkScrollPane;

    @FXML
    private ImageView imv;
    
    @FXML
    private RadioButton sculpRadioButton;
    
    @FXML 
    private RadioButton paintRadioButton;
    
    @FXML 
    private RadioButton allRadioButton;
    
    @FXML
    private BorderPane centerPane;



    /**
     * Empty constructor.
     */
    public BrowseAuctionController() {
    }

    /**
     * Sets username.
     * @param username username of logged in user.
     */
    public BrowseAuctionController(String username) {
        this.username = username;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //gets all auctions being sold that is not by you.
        String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid and auctioncomp = 0 and auction.seller <> '" + this.username + "';";
        getImages(FXCollections.observableArrayList(artworkDatabaseManager.getAllArtworks(sqlSelect)));
        allRadioButton.setSelected(true);
    }
    



    /**
     * Gets all artworks currently in auction. Displays them.
     */

    public void getImages(ObservableList<Artwork> observeArrayList) {




        Stage stage = new Stage();

        ArrayList<String> artworkPhoto = new ArrayList<>();

        /*
        if(sculpRadioButton.isSelected()){

        	observeArrayList = FXCollections.observableArrayList(sculptureArrayList);
        } else if (paintRadioButton.isSelected()) {
        	observeArrayList = FXCollections.observableArrayList(paintingArrayList);
        } else if (allRadioButton.isSelected()) {
        	observeArrayList = FXCollections.observableArrayList(artworkArrayList);
        } else {
        	allRadioButton.setSelected(true);
        	observeArrayList = FXCollections.observableArrayList(artworkArrayList);

        }
        */



        Image[] images = new Image[observeArrayList.size()];
        ImageView[] imageViews = new ImageView[observeArrayList.size()];
        VBox[] vBoxes = new VBox[observeArrayList.size()]; //vboxs to add in grid pane.


        artworkScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); //scroller can't move horizontally.
        artworkScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); //scroller can move vertically.
        artworkScrollPane.setFitToHeight(true);
        artworkScrollPane.setContent(artworkTilePane);
        artworkTilePane.setHgap(GAP);
        artworkTilePane.setVgap(GAP);

        //Get location of artwork photos.
        for (Artwork artwork : observeArrayList) {
            artworkPhoto.add(artwork.getPhoto());
        }

        String[] imageLocation = artworkPhoto.toArray(new String[observeArrayList.size()]); //convert array list to array.

        for (int i = 0; i < imageLocation.length; i++) {

            final int currentI = i;
            images[i] = new Image(imageLocation[i], IMAGE_WIDTH, 0, true, true); //get image.
            imageViews[i] = new ImageView(images[i]); //add image to image view.
            imageViews[i].setFitWidth(IMAGE_WIDTH);
            imageViews[i].setFitHeight(stage.getHeight() - 10);
            imageViews[i].setPreserveRatio(true);
            imageViews[i].setSmooth(true);
            imageViews[i].setCache(true);

            //Add event handler.
            //Opens show auction when clicking on an auction for sale.
            imageViews[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	
                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/ShowAuction.fxml"));
                	
                	//creates new controller
                	ShowAuctionController showAuctionController = new ShowAuctionController();
                	
                	showAuctionController.setUsername(getUsername());
                	
                	showAuctionController.setPhoto(imageLocation[currentI]); //photo location.
                	//set controller manually
                	fxmlLoader.setController(showAuctionController);
					
               	 
            			try {
            				centerPane.setCenter(fxmlLoader.load()); //set the center of the pane to show auction scene
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                }
            });

            vBoxes[i] = new VBox();
            vBoxes[i].getChildren().addAll(imageViews[i]); //add vbox inside gridpane.
            artworkTilePane.getChildren().add(vBoxes[i]); //add image to gridpane.
            artworkTilePane.setAlignment(Pos.CENTER);

        }


    }



    
    @FXML
    public void sculpSelected() {

        String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid and auctioncomp = 0 and artwork.typeOfArtwork = 'sculpture' and auction.seller <> '" +
                 this.username + "';";

        artworkTilePane.getChildren().clear(); //delete all previous artworks.


        getImages(FXCollections.observableArrayList(artworkDatabaseManager.getAllArtworks(sqlSelect)));

    }


    @FXML
    void paintSelected(ActionEvent event) {

        String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid and auctioncomp = 0 and artwork.typeOfArtwork = 'painting' and auction.seller <> '"
                + this.username + "';";

        artworkTilePane.getChildren().clear(); //delete all previous artworks.
        getImages(FXCollections.observableArrayList(artworkDatabaseManager.getAllArtworks(sqlSelect)));

    }

    @FXML
    void favouriteSelected(ActionEvent event) {
        //TODO
        String sqlSelect = "Select distinct artworkid, typeofartwork, title, description, photo, nameofcreator, reservedprice, dateentered," +
                "bidsallowed, mainmaterial, extraphotos, width, height, depth " +
                "from auction, artwork, favouriteuser where auction.auctionid = artwork.artworkid " +
                "and auction.seller in (select username2 from favouriteuser where username1 = '"
                + this.username + "');";

        /*
          artworkArrayList.add(new Sculpture(resultSet.getInt("artworkid"), resultSet.getString("typeofartwork"), resultSet.getString("title"), resultSet.getString("description"),
                           resultSet.getString("photo"), resultSet.getString("nameofcreator"), resultSet.getDouble("reservedprice"),
                           resultSet.getString("dateentered"), resultSet.getInt("bidsallowed"), resultSet.getString("mainmaterial"),
                           resultSet.getString("extraphotos"),
                           resultSet.getDouble("width"),
                           resultSet.getDouble("height"), resultSet.getDouble("depth")));
         */
        artworkTilePane.getChildren().clear(); //delete all previous artworks.
        getImages(FXCollections.observableArrayList(artworkDatabaseManager.getAllArtworks(sqlSelect)));


    }

    @FXML
    void allSelected(ActionEvent event) {
        String sqlSelect = "Select * from artwork, auction where auction.auctionid = artwork.artworkid and auctioncomp = 0 and auction.seller <> '" + this.username + "';";
        artworkTilePane.getChildren().clear(); //delete all previous artworks.
        getImages(FXCollections.observableArrayList(artworkDatabaseManager.getAllArtworks(sqlSelect)));


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
