package com.springsun.nimjfx.view.appView;

import com.springsun.nimjfx.model.ListOfHeaps;
import com.springsun.nimjfx.model.ListOfPlayers;
import com.springsun.nimjfx.view.IView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstSceneController {
    IView v;
    ObservableList<Integer> qtyList = FXCollections.observableArrayList(0, 1, 2, 3);
    private static Logger log = Logger.getLogger(FirstSceneController.class.getName());

    private static ListOfHeaps listOfHeaps;
    private static ListOfPlayers listOfPlayers;
    private static IView vstat;
    private int numberOfComputers;
    private int numberOfMadComputers;
    private int numberOfHumans = 1;
    private int numberOfHeaps = 4;
    private int numberOfStones = 10;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label rules;
    @FXML
    private Label QtyComp;
    @FXML
    private Label QtyMadComp;

    @FXML
    private ImageView comp;
    @FXML
    private ImageView madcomp;

    @FXML
    private ChoiceBox choiceBoxComp;
    @FXML
    private ChoiceBox choiceBoxMadComp;

    @FXML
    private Button start;
    @FXML
    private Button exit;

    @FXML
    private void initialize(){
        try {
            v = new AppView();
            vstat = v;
            rules.setText("The Nim Game is a game where players pick up some stones from heaps. A player should " +
                    "take stones (or just one stone) from one of the heaps. Those who will take last stone from last " +
                    "non-empty heap will win. \n \n Choose your opponents and press 'Start Nim Game' button to play.");
            QtyComp.setText("Quantity of computer opponents: ");
            QtyMadComp.setText("Quantity of mad computer opponents: ");
            setImageComp();
            setImageMad();

            choiceBoxComp.setItems(qtyList);
            choiceBoxComp.setValue(1);
            choiceBoxMadComp.setItems(qtyList);
            choiceBoxMadComp.setValue(0);
            choiceBoxComp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>(){
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    if (((int) choiceBoxComp.getValue() == 0) & ((int) choiceBoxMadComp.getValue() == 0)){
                        choiceBoxMadComp.setValue(1);
                    }
                }
            });
            choiceBoxMadComp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>(){
                @Override
                public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                    if (((int) choiceBoxComp.getValue() == 0) & ((int) choiceBoxMadComp.getValue() == 0)){
                        choiceBoxComp.setValue(1);
                    }
                }
            });
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in FirstSceneController initialize(): ", e);
        }
    }

    @FXML
    private void startHandler(ActionEvent actionEvent){
        try {
            numberOfComputers = (int) choiceBoxComp.getValue();
            numberOfMadComputers = (int) choiceBoxMadComp.getValue();
            v.setNumberOfComputers(numberOfComputers);
            v.setNumberOfMadComputers(numberOfMadComputers);
            v.setNumberOfHumans(numberOfHumans);
            v.setNumberOfHeaps(numberOfHeaps);
            v.setNumberOfStones(numberOfStones);
            listOfHeaps = v.createHeaps();
            listOfPlayers = v.createPlayers();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in FirstSceneController startHandler(): ", e);
        }
    }

    @FXML
    private void exitHandler(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/NoticeExitInTheEnd.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setTitle("Notice");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in FirstSceneController exitHandler(): ", e);
        }
    }

    private void setImageComp(){
        URL url = MainSceneController.class.getResource("/media/images/architector.jpg");
        comp.setImage(new Image(url.toString()));
    }

    private void setImageMad(){
        URL url = MainSceneController.class.getResource("/media/images/bender.jpg");
        madcomp.setImage(new Image(url.toString()));
    }

    public static ListOfHeaps getListOfHeaps() {
        return listOfHeaps;
    }

    public static ListOfPlayers getListOfPlayers() {
        return listOfPlayers;
    }

    public static IView getV() {
        return vstat;
    }

}
