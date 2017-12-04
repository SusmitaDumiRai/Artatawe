package co.uk.artatawe.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Susi on 03/12/2017.
 */
public class BrowseAuctionController implements Initializable {

    private String username;

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



}
