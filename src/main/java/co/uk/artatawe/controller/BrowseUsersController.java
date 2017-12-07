package co.uk.artatawe.controller;

/**
 * Handles browse user fxml file.
 * Displays all users and the ability to un/favourite them.
 *
 * @author 914937
 * @author 908928
 */
import co.uk.artatawe.database.FavouriteUserDatabaseManager;
import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.main.FavouriteUsers;
import co.uk.artatawe.main.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BrowseUsersController implements Initializable {

    private String username; //logged in user.

    @FXML
    private TilePane tilePane;

    @FXML
    private ScrollPane scrollPane;

    ArrayList<String> favouriteUserNames = new ArrayList<>();

    /**
     * Empty constructor
     */
    public BrowseUsersController() {

    }

    /**
     * Sets username.
     * @param username username of logged in user.
     */
    public BrowseUsersController(String username) {
        this.username = username;
    }

    public ArrayList<FavouriteUsers> getAllFavouriteUsers() {

        FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();
        String sqlSelect = "select * from favouriteuser;";
        return favouriteUserDatabaseManager.getFavouriteUsers(sqlSelect); //create an array of favourite users
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUserProfiles();
        for (FavouriteUsers favouriteUsers: getAllFavouriteUsers()) {
            if (favouriteUsers.getUser1().getUserName().equals(this.username)) {
                favouriteUserNames.add(favouriteUsers.getUser2().getUserName());
            }
        }

       // for (String username: favouriteUserNames) {
        //    if () {

          //  }
        //}
    }

    /**
     * Displays all users' profile image and their username.
     * Also displays heart button to favourite/unfavourite users.
     */
    public void getUserProfiles() {
        Stage stage = new Stage();

        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

     //   String sqlSelect = "Select * from user where username <> '" + this.username + "'";";
        String sqlSelect = "Select * from user where username <> 'username';";

        ArrayList<User> userArrayList = userDatabaseManager.getAllUsers(sqlSelect);

        ArrayList<String> userIcon = new ArrayList<>();
        ArrayList<String> allUsernames = new ArrayList<>();

        Image[] icons = new Image[userArrayList.size()];
        ImageView[] imageViews = new ImageView[userArrayList.size()];
        VBox[] vBoxes = new VBox[userArrayList.size()];

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(tilePane);
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        //gets all usernames and profile images
        for (User user: userArrayList) {
            userIcon.add(user.getProfileImage());
            allUsernames.add(user.getUserName());
        }

        String[] usernameArray = allUsernames.toArray(new String[userArrayList.size()]);
        String[] imageLocation = userIcon.toArray(new String[userArrayList.size()]);

        //Display in GUI.
        for (int i = 0; i < imageLocation.length; i++) {

            icons[i] = new Image(imageLocation[i], 150, 0, true, true);
            imageViews[i] = new ImageView(icons[i]);
            imageViews[i].setFitWidth(150);
            imageViews[i].setFitHeight(stage.getHeight() - 10);
            imageViews[i].setPreserveRatio(true);
            imageViews[i].setSmooth(true);
            imageViews[i].setCache(true);

            Button heartButton = new Button();
            Image heartIcon = new Image(("co/uk/artatawe/gui/Icons/icons8-heart-40.png"));
            heartButton.setGraphic(new ImageView(heartIcon));
            heartButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: transparent");

            vBoxes[i] = new VBox();

            vBoxes[i].getChildren().addAll(imageViews[i]);
            heartButton.setText(usernameArray[i]);

            vBoxes[i].getChildren().add(heartButton);
            tilePane.getChildren().add(vBoxes[i]); //add image to gridpane.

            final int currentI = i;


            heartButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {

                    FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();

                    String sqlInsert = "insert into favouriteuser(username1,username2) values (" +
                    "'username'," + "'" + usernameArray[currentI] + "');";

                    System.out.println(sqlInsert);

                    /*
                    username = " + this.username +"
                    String sqlInsert = "insert into favouriteuser(username1,username2) values (" +
                    "'username'," + "'username');";
                    favouriteUserDatabaseManager.executeStatement(sqlInsert);
                    */

                    Image fullHeart = new Image(("co/uk/artatawe/gui/Icons/icons8-love-50.png"));
                    heartButton.setGraphic(new ImageView(fullHeart));

                    String sqlSelect = "select * from favouriteuser;";
                    ArrayList<FavouriteUsers> favouriteUserArrayList = favouriteUserDatabaseManager.getFavouriteUsers(sqlSelect); //create an array of favourite users
                    User user1 = userDatabaseManager.getUser("username");
                    User user2 = userDatabaseManager.getUser("uglybackpack");

                    FavouriteUsers favouriteUser = new FavouriteUsers(user1, user2 );

                    if(favouriteUserArrayList.contains(favouriteUser)) {

                    }

                }
            });


        }
    }


    /**
     * Gets logged in user's username.
     * @return username of logged in user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets logged in user's username.
     * @param username username of logged in user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

}

