package com.springsun.nimjfx.view.appView;

import com.springsun.nimjfx.controller.*;
import com.springsun.nimjfx.model.ListOfHeaps;
import com.springsun.nimjfx.model.ListOfPlayers;
import com.springsun.nimjfx.model.Players;
import com.springsun.nimjfx.view.IView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.springsun.nimjfx.controller.ComputerMove.moveComputer;
import static com.springsun.nimjfx.controller.MadComputerMove.moveMadComputer;
import static com.springsun.nimjfx.model.Players.HUMAN;

public class MainSceneController {
    private static Logger log = Logger.getLogger(MainSceneController.class.getName());
    private ObservableList<Integer> listOfHeapsObservableList;
    private ObservableList<Players> listOfPlayersObservableList;
    private ObservableList<String> listOfMessages = FXCollections.observableArrayList();
    private IView v;

    private ListOfHeaps listOfHeaps;
    private ListOfPlayers listOfPlayers;
    private int numberOfHeap, numberOfStones, sum, counter;
    private CopyOnWriteArrayList<ComboBox<Integer>> comboBoxList = new CopyOnWriteArrayList<>();
    private Label[] heapLabelList;
    private ImageView[] heapImagesList;
    private Players player;
    private MediaPlayer mediaPlayer;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label heap1;
    @FXML
    private Label heap2;
    @FXML
    private Label heap3;
    @FXML
    private Label heap4;
    @FXML
    private Label message;

    @FXML
    private ImageView picture1;
    @FXML
    private ImageView picture2;
    @FXML
    private ImageView picture3;
    @FXML
    private ImageView picture4;

    @FXML
    private ComboBox<Integer> comboBox1;
    @FXML
    private ComboBox<Integer> comboBox2;
    @FXML
    private ComboBox<Integer> comboBox3;
    @FXML
    private ComboBox<Integer> comboBox4;

    @FXML
    private Button makeMoveButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button showStatisticsButton;
    @FXML
    private Button exitButton;


    @FXML
    private void initialize() {
        try {
            listOfHeaps = FirstSceneController.getListOfHeaps();
            listOfPlayers = FirstSceneController.getListOfPlayers();
            v = FirstSceneController.getV();
            listOfHeapsObservableList = listOfHeaps.getListOfHeaps();
            listOfPlayersObservableList = listOfPlayers.getPlayers();
            fillListOfMessages();
            message.setText(listOfMessages.get(0));
            comboBoxList.add(0, comboBox1);
            comboBoxList.add(1, comboBox2);
            comboBoxList.add(2, comboBox3);
            comboBoxList.add(3, comboBox4);
            heapLabelList = new Label[]{heap1, heap2, heap3, heap4};
            heapImagesList = new ImageView[]{picture1, picture2, picture3, picture4};
            boxSet(listOfHeapsObservableList);
            labelSet(listOfHeapsObservableList);
            URL url = MainSceneController.class.getResource("media/sounds/soundOfStones.mp3");
            Media soundFile = new Media(url.toString());
            imagesSet(listOfHeapsObservableList);
            makeMoveButton.setDisable(true);
            counter = 0;
            listOfHeapsObservableList.addListener((ListChangeListener<? super Integer>) observable -> {
                boxSet(listOfHeapsObservableList);
                labelSet(listOfHeapsObservableList);
                imagesSet(listOfHeapsObservableList);
                mediaPlayer = new MediaPlayer(soundFile);
                mediaPlayer.setVolume(0.2);
                mediaPlayer.play();
            });

            for (int i = 0; i < comboBoxList.size(); i++) {
                int j = i;
                comboBoxList.get(i).setOnAction(event -> {
                    sum = 0;
                    for (int k = 0; k < comboBoxList.size(); k++) {
                        sum += comboBoxList.get(k).getValue();
                    }
                    if (sum == 0) {
                        makeMoveButton.setDisable(true);
                    } else {
                        if (comboBoxList.get(j).getValue() > 0) {
                            for (int k = 0; k < comboBoxList.size(); k++) {
                                if (k != j) {
                                    comboBoxList.get(k).setValue(0);
                                }
                            }
                        }
                        makeMoveButton.setDisable(false);
                    }
                });
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in MainSceneController initialize(): ", e);
        }
    }

    @FXML
    private void makeMoveHandler(ActionEvent actionEvent){
        try {
            mark:
            {
                for (int i = 0; i < comboBoxList.size(); i++) {
                    if ((int) comboBoxList.get(i).getValue() > 0) {
                        numberOfHeap = i;
                        numberOfStones = listOfHeapsObservableList.get(i) - (int) comboBoxList.get(i).getValue();
                    }
                }
                listOfHeapsObservableList.set(numberOfHeap, numberOfStones);
                if (!ViktoryCondition.nobodyHasWonYet(listOfHeaps.getListOfHeaps())) {
                    v.setWinnerName(Players.HUMAN.toString());
                    v.setEnumWinner(Players.HUMAN);
                    v.winnerAnnouncement(DefinePlayer.definePlayer(listOfPlayers, counter), rootPane);
                    break mark;
                }
                counter++;
                player = listOfPlayers.getPlayers().get(counter);
                while (player != HUMAN) {

                    makeMoveButton.setDisable(true);
                    switch (player) {
                        case COMPUTER:
                            message.setText(listOfMessages.get(1));
    //                        try {
    //                            Thread.sleep(500);
    //                        } catch (InterruptedException e) {
    //                            e.printStackTrace();
    //                        }
                            moveComputer(listOfHeaps);
                            if (!ViktoryCondition.nobodyHasWonYet(listOfHeaps.getListOfHeaps())) {
                                v.setWinnerName(Players.COMPUTER.toString());
                                v.setEnumWinner(Players.COMPUTER);
                                v.winnerAnnouncement(DefinePlayer.definePlayer(listOfPlayers, counter), rootPane);
                                break mark;
                            }
                            break;
                        case MAD_COMPUTER:
                            message.setText(listOfMessages.get(2));
                            moveMadComputer(listOfHeaps);
                            if (!ViktoryCondition.nobodyHasWonYet(listOfHeaps.getListOfHeaps())) {
                                v.setWinnerName(Players.MAD_COMPUTER.toString());
                                v.setEnumWinner(Players.MAD_COMPUTER);
                                v.winnerAnnouncement(DefinePlayer.definePlayer(listOfPlayers, counter), rootPane);
                                break mark;
                            }
                            break;
                        default:
                            log.severe("No matches in method makeMove");
                            break;
                    }
                    counter++;
                    if (counter == listOfPlayers.getPlayers().size()) {
                        counter = 0;
                    }
                    player = listOfPlayers.getPlayers().get(counter);
                }
                message.setText(listOfMessages.get(0));
            }
        } catch (IndexOutOfBoundsException e) {
            log.log(Level.SEVERE, "Exception caught in MainSceneController makeMoveHandler(): ", e);
        } catch (NullPointerException e) {
            log.log(Level.SEVERE, "Exception caught in MainSceneController makeMoveHandler(): ", e);
        }
    }

    @FXML
    private void newGameHandler(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NoticeNewGame.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setTitle("Notice");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in MainSceneController newGameHandler(): ", e);
        }
    }

    @FXML
    private void showStatisticsHandler(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowStatistics.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setTitle("Total Score");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in MainSceneController showStatisticsHandler(): ", e);
        }
    }

    @FXML
    private void exitHandler(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Notice.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setTitle("Notice");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in MainSceneController exitHandler(): ", e);
        }
    }

    private void boxSet(ObservableList<Integer> listOfHeapsObservableList){
        Iterator<ComboBox<Integer>> it = comboBoxList.iterator();
        int i = 0;
        ComboBox<Integer> ch;
        while (it.hasNext()){
            ch = it.next();
            ch.getItems().setAll(GetListOfItems.getList(listOfHeapsObservableList.get(i)));
            ch.setValue(0);
            i++;
        }
    }

    private void labelSet(ObservableList<Integer> listOfHeapsObservableList){
        for (int i = 0; i < heapLabelList.length; i++){
            heapLabelList[i].setText(listOfHeapsObservableList.get(i).toString());
        }
    }

    private void imagesSet(ObservableList<Integer> listOfHeapsObservableList){
        int stones;
        String pathAsString;
        for (int i = 0; i < heapImagesList.length; i++){
            stones = listOfHeapsObservableList.get(i);
            URL url = MainSceneController.class.getResource("media/images/image-" + stones + ".jpg");
            pathAsString = url.toString();
            heapImagesList[i].setImage(new Image(pathAsString));
        }
    }

    public AnchorPane getRootPane() {
        return rootPane;
    }

    private void fillListOfMessages() {
        listOfMessages.add(0, "Choose a heap with stones and choose quantity of stones to pick from it."
                + "\n Than press 'Make Move' button");
        listOfMessages.add(1, "Player Computer moves...");
        listOfMessages.add(2, "Player Mad Computer moves...");
    }

}
