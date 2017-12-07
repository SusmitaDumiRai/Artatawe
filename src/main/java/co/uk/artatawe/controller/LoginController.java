
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.net.URL;
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
      signIn(event);
    }
    
    @FXML
    void onEnter(ActionEvent event) {
      signIn(event);
    }
    
    private void signIn(ActionEvent event) {
    	if (validateUsername()) {
            Parent root;
            try {

                NavigationController navigationController = new NavigationController();
                navigationController.setUsername(username.getText());


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("co/uk/artatawe/gui/Navigation.fxml"));
                fxmlLoader.setController(navigationController);

                root = fxmlLoader.load();


                Stage stage = new Stage();
                stage.setTitle("Artatawe");
                stage.setScene(new Scene(root, WIDTH, HEIGHT));

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


