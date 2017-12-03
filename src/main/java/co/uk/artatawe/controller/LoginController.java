package co.uk.artatawe.controller;

/**
 * Created by Plamena on 3.12.2017 г..
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
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
        String user = username.getText();

        if(user.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a username");
            alert.showAndWait();
            return;
        }
    }

}

