
package co.uk.artatawe.controller;

/**
 * Controller for login page.
 *
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private final int WIDTH = 800; //width size for browse auction window.
    private final int HEIGHT = 600; //height size for browse auction window.
    private final String ERROR = "Wrong username";

    @FXML
    private Label errorMessage;

    @FXML
    private Button signInButton;

    @FXML
    private TextField username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    /**
     * Validates user password, if correct, opens browse auction window.
     */
    @FXML
    void signInUser(ActionEvent event) {
        if (validateUsername()) {
            Parent root;
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/BrowseAuctions.fxml"));

               root = fxmlLoader.load();


                BrowseAuctionController browseAuctionController = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.setTitle("Browsing artworks");
                stage.setScene(new Scene(root, WIDTH, HEIGHT));

                browseAuctionController.setUsername(username.getText()); //parse username.

                stage.show(); //display browse auctions.

                //hides current window.
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            errorMessage.setTextFill(Paint.valueOf("RED"));
        }
    }


    /**
     * Validates username.
     * @return true of username is valid.
     */
    public boolean validateUsername() {
        String usernameText = username.getText();
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager();

        if (!usernameText.isEmpty()) {
            for (String username1 : userDatabaseManager.getAllUsernames()) {
                if (usernameText.equals(username1)) {
                    return true;

                }
            }
        }
        return false;
    }



}


