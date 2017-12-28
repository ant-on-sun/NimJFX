package com.springsun.nimjfx.view.appView;

import com.springsun.nimjfx.controller.TotalScoreContent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowStatisticsController {
    private static Logger log = Logger.getLogger(ShowStatisticsController.class.getName());

    @FXML
    private Label score;
    @FXML
    private Button okButton;

    @FXML
    private void initialize(){
        try {
            score.setText(TotalScoreContent.getContentAsString());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in ShowStatisticsController initialize(): ", e);
        }
    }

    @FXML
    public void okHandle(ActionEvent actionEvent){
        try {
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in ShowStatisticsController okHandle(): ", e);
        }
    }

}
