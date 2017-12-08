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

    public ArrayList<User> getAllFavouriteUsers() {

        FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();
        String sqlSelect = "select * from favouriteuser;";
        ArrayList<User> favs = new ArrayList<>();

        for (FavouriteUsers favouriteUsers : favouriteUserDatabaseManager.getFavouriteUsers(sqlSelect)) {
            if (favouriteUsers.getUser1().getUserName().equals(this.username)) {
                favs.add(favouriteUsers.getUser2());
            }
        }

        return favs;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.username = "username";
        getUserProfiles();
    }

    private boolean isFavouriteOf(User user) {
        for (User fav : getAllFavouriteUsers()) {
            if (fav.getUserName().equals(user.getUserName())) {
                return true;
            }
        }

        return false;
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

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(tilePane);
        tilePane.setHgap(10);
        tilePane.setVgap(10);

        //gets all usernames and profile images
        for (User user : userDatabaseManager.getAllUsers(sqlSelect)) {

            Image icon = new Image(user.getProfileImage(), 150, 0, true, true);
            ImageView imageView = new ImageView(icon);
            imageView.setFitWidth(150);
            imageView.setFitHeight(stage.getHeight() - 10);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);

            Button heartButton = new Button();

            Image heartIcon = new Image(("co/uk/artatawe/gui/Icons/icons8-heart-40.png"));

            if (isFavouriteOf(user)) {
                heartIcon = new Image("co/uk/artatawe/gui/Icons/icons8-love-50.png");
            }

            heartButton.setGraphic(new ImageView(heartIcon));
            heartButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: transparent");

            VBox vBox = new VBox();

            vBox.getChildren().addAll(imageView);
            heartButton.setText(user.getUserName());

            vBox.getChildren().add(heartButton);
            tilePane.getChildren().add(vBox); //add image to gridpane.

            heartButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {

                    FavouriteUserDatabaseManager favouriteUserDatabaseManager = new FavouriteUserDatabaseManager();

                    if (isFavouriteOf(user)) {
                        String sqlDelete = "delete from favouriteuser where username1 = '" + username
                                + "' and username2 = '" + user.getUserName() + "';";

                        favouriteUserDatabaseManager.executeStatement(sqlDelete);
                        System.out.println(sqlDelete);

                        Image fullHeart = new Image(("co/uk/artatawe/gui/Icons/icons8-heart-40.png"));
                        heartButton.setGraphic(new ImageView(fullHeart));
                    } else {
                        String sqlInsert = "insert into favouriteuser(username1,username2) values ('" +
                                username + "', '" + user.getUserName() + "');";

                        favouriteUserDatabaseManager.executeStatement(sqlInsert);
                        System.out.println(sqlInsert);

                        Image fullHeart = new Image(("co/uk/artatawe/gui/Icons/icons8-love-50.png"));
                        heartButton.setGraphic(new ImageView(fullHeart));
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

