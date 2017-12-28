package com.springsun.nimjfx.view;

import com.springsun.nimjfx.model.ListOfHeaps;
import com.springsun.nimjfx.model.ListOfPlayers;
import com.springsun.nimjfx.model.Players;
import javafx.scene.layout.AnchorPane;

public interface IView {
    void showRules();
    void getInitialConditions();
    void setNumberOfHumans(int numberOfHumans);
    void setNumberOfComputers(int numberOfComputers);
    void setNumberOfMadComputers(int numberOfMadComputers);
    void setNumberOfHeaps(int numberOfHeaps);
    void setNumberOfStones(int numberOfStones);
    ListOfHeaps createHeaps();
    ListOfPlayers createPlayers();
    void showCurrentStateOfHeaps(ListOfHeaps h);
    void moveHuman(ListOfHeaps h, AnchorPane rootPane);
    void showMessage(String player);
    void winnerAnnouncement(String winner, AnchorPane rootPane);

    String getWinner();
    void setWinnerName(String winnerName);
    String getWinnerName();
    void setEnumWinner(Players player);
    Players getEnumWinner();
}
