package com.springsun.nimjfx.view.appView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NoticeExitInTheEndController {
    private static Logger log = Logger.getLogger(NoticeExitInTheEndController.class.getName());

    @FXML
    private Label msgLable;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;

    @FXML
    private void initialize(){
        try {
            msgLable.setText("Are you sure want to exit?");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in NoticeExitInTheEndController initialize(): ", e);
        }
    }

    @FXML
    public void yesButtonHandler(ActionEvent actionEvent){
        Platform.exit();
    }

    @FXML
    public void noButtonHandle(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) noButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in NoticeExitInTheEndController noButtonHandler(): ", e);
        }
    }

}
