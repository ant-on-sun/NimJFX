package com.springsun.nimjfx.view.appView;

import com.springsun.nimjfx.model.ListOfHeaps;
import com.springsun.nimjfx.model.ListOfPlayers;
import com.springsun.nimjfx.model.Players;
import com.springsun.nimjfx.view.IView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.logging.Logger;

public class AppView implements IView {
    private int numberOfHumans;
    private int numberOfComputers;
    private int numberOfMadComputers;
    private int numberOfHeaps;
    private int numberOfStones;

    private String winner;
    private String winnerName = "";
    private Players winnerEnum;

    private static Logger log = Logger.getLogger(AppView.class.getName());

    @Override
    public void showRules() {
        //The rules are shown on first scene
    }

    @Override
    public void getInitialConditions() {
    }

    @Override
    public ListOfHeaps createHeaps() {
        ListOfHeaps listOfHeaps = new ListOfHeaps.Builder()
                .numberOfHeaps(numberOfHeaps)
                .numberOfStones(numberOfStones)
                .createListOfHeaps();
        log.fine("Create heaps");
        return listOfHeaps;
    }

    @Override
    public ListOfPlayers createPlayers() {
        ListOfPlayers listOfPlayers = new ListOfPlayers.Builder()
                .numberOfHumans(numberOfHumans)
                .numberOfComputers(numberOfComputers)
                .numberOfMadComputers(numberOfMadComputers)
                .createListOfPlayers();
        log.fine("Create players");
        return listOfPlayers;
    }

    @Override
    public void showCurrentStateOfHeaps(ListOfHeaps h) {
    }

    @Override
    public void moveHuman(ListOfHeaps h, AnchorPane rootPane) {
    }

    @Override
    public void showMessage(String player) {
    }

    @Override
    public void winnerAnnouncement(String winner, AnchorPane rootPane) {
        this.winner = winner;
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("LastScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.fine("Winner announcement");
        rootPane.getChildren().setAll(pane);
    }

    @Override
    public void setNumberOfHumans(int numberOfHumans) {
        this.numberOfHumans = numberOfHumans;
    }

    @Override
    public void setNumberOfComputers(int numberOfComputers) {
        this.numberOfComputers = numberOfComputers;
    }

    @Override
    public void setNumberOfMadComputers(int numberOfMadComputers) {
        this.numberOfMadComputers = numberOfMadComputers;
    }

    @Override
    public void setNumberOfHeaps(int numberOfHeaps) {
        this.numberOfHeaps = numberOfHeaps;
    }

    @Override
    public void setNumberOfStones(int numberOfStones) {
        this.numberOfStones = numberOfStones;
    }

    public String getWinner() {
        return this.winner;
    }

    @Override
    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    @Override
    public String getWinnerName() {
        return winnerName;
    }

    @Override
    public void setEnumWinner(Players player){
        this.winnerEnum = player;
    }

    @Override
    public Players getEnumWinner(){
        return winnerEnum;
    }

}
