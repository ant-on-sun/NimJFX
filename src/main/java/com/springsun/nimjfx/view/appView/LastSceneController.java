package com.springsun.nimjfx.view.appView;

import com.springsun.nimjfx.controller.SetTotalScore;
import com.springsun.nimjfx.controller.TotalScoreContent;
import com.springsun.nimjfx.model.Players;
import com.springsun.nimjfx.view.IView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.springsun.nimjfx.model.Players.HUMAN;

public class LastSceneController {
    IView v;
    private MediaPlayer mediaPlayer;
    private static Logger log = Logger.getLogger(LastSceneController.class.getName());

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label winner;
    @FXML
    private Label scoreHeader;
    @FXML
    private Label totalScore;
    @FXML
    private Button newGameBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private ImageView image;

    @FXML
    private void initialize(){
        try {
            v = FirstSceneController.getV();
            winner.setText(v.getWinner());
            String pathToGif = pathToGifToShow(v.getEnumWinner());
            String pathToSound = pathToSoundToPlay(v.getEnumWinner());
            image.setImage(new Image(pathToGif));
            scoreHeader.setText("Total Score:");
            SetTotalScore.setScore(v.getWinnerName());
            totalScore.setText(TotalScoreContent.getContentAsString());
            Media soundFile = new Media(pathToSound);
            mediaPlayer = new MediaPlayer(soundFile);
            mediaPlayer.setVolume(0.2);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in LastSceneController initialize(): ", e);
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
            log.log(Level.SEVERE, "Exception caught in LastSceneController exitHandler(): ", e);
        }
    }

    @FXML
    private void newGameHandler(ActionEvent actionEvent){
        AnchorPane pane = null;
        mediaPlayer.stop();
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxml/FirstScene.fxml"));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in LastSceneController newGameHandler(): ", e);
        }
        rootPane.getChildren().setAll(pane);
    }

    private String pathToGifToShow(Players winnerEnum) {
        URL url = null;
        switch (winnerEnum){
            case HUMAN:
                url = LastSceneController.class.getResource("/media/gifs/salut.gif");
                System.out.println(url.toString());
                break;
            case COMPUTER:
                url = LastSceneController.class.getResource("/media/gifs/computer.gif");
                System.out.println(url.toString());
                break;
            case MAD_COMPUTER:
                url = LastSceneController.class.getResource("/media/gifs/madcomp.gif");
                System.out.println(url.toString());
                break;
            default:
                log.severe("No matches in LastSceneController method pathToGifToShow()");
                break;

        }
        return url.toString();
    }

    private String pathToSoundToPlay(Players winnerEnum){
        URL url = null;
        switch (winnerEnum){
            case HUMAN:
                url = LastSceneController.class.getResource("/media/sounds/fireworks.mp3");
                System.out.println(url.toString());
                break;
            case COMPUTER:
                url = LastSceneController.class.getResource("/media/sounds/computer.mp3");
                System.out.println(url.toString());
                break;
            case MAD_COMPUTER:
                url = LastSceneController.class.getResource("/media/sounds/IrishDance.mp3");
                System.out.println(url.toString());
                break;
            default:
                log.severe("No matches in LastSceneController method pathToSoundToPlay()");
                break;

        }
        return url.toString();
    }

}
