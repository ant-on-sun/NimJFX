package com.springsun.nimjfx.view.appView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoticeNewGameController {
    private static Logger log = Logger.getLogger(NoticeNewGameController.class.getName());

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label msgLable;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;

    @FXML
    private void initialize(){
        try {
            msgLable.setText("The game in not finished yet \nand it's current state will be lost.\n" +
                    "Are you sure want to start New Game?");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in NoticeNewGameController initialize(): ", e);
        }
    }

    @FXML
    public void yesButtonHandler(ActionEvent actionEvent){
        try {
            AnchorPane pane = null;
            Stage stage = (Stage) yesButton.getScene().getWindow();
            AnchorPane rootPane = (AnchorPane) stage.getOwner().getScene().getRoot();
            pane = FXMLLoader.load(getClass().getResource("/fxml/FirstScene.fxml"));
            rootPane.getChildren().setAll(pane);
            stage.close();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in NoticeNewGameController yesButtonHandler(): ", e);
        }
    }

    @FXML
    public void noButtonHandle(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) noButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in NoticeNewGameController noButtonHandle(): ", e);
        }
    }

}
