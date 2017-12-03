package co.uk.artatawe.controller;

/**
 * Controller for login page.
 * @author Plamena Tseneva
 * @author 908928
 *
 * @version 1.0
 */
import co.uk.artatawe.database.UserDatabaseManager;
import co.uk.artatawe.sample.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button signInButton;

    @FXML
    private TextField username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }





    @FXML
    void signInUser(ActionEvent event) {
        String usernameText = username.getText();

        if (!usernameText.isEmpty()) {
           for (String username1 : getAllUsernames()) {
               if (usernameText.equalsIgnoreCase(username1)) {
                   System.out.println("valid username.");
                   //TODO update to display browse artworks.

                   Parent root;

                   try {

                       root = FXMLLoader.load(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseArtwork.fxml"));
                       Stage stage = new Stage();
                       stage.setTitle("Browsing artworks");
                       stage.setScene(new Scene(root, 400, 400)); //TODO UPDATE TO NO MAGIC NUMBERS. GL LENI.
                       stage.show();

                       //hides current window.
                       ((Node) (event.getSource())).getScene().getWindow().hide();
                   } catch (IOException ex) {
                       System.out.println(ex.getMessage());
                   }


               }
           }
        }

    }

    /**
     * Gets array list of usernames.
     * @return array list of usernames.
     */
    public ArrayList<String> getAllUsernames() {
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

        ArrayList<String> usernameArrayList = new ArrayList<>();

        for (User user :  userDatabaseManager.getAllUsers()) {
            usernameArrayList.add(user.getUserName().toLowerCase());
        }

        return usernameArrayList;

    }

    /*
    @FXML
    public void signInUser(ActionEvent event) {
        String user = username.getText();

        if (user.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a username");
            alert.showAndWait();
            return;
        }
    }
    */



}

