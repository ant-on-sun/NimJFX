package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.ListOfPlayers;
import com.springsun.nimjfx.model.Players;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefinePlayer {
    private static Logger log = Logger.getLogger(DefinePlayer.class.getName());

    public static String definePlayer(ListOfPlayers players, int i)
            throws IndexOutOfBoundsException, NullPointerException {
        int j = 0;
        String s = "";
        Players playerName = null;
        try {
            playerName = players.getPlayers().get(i);
            j = players.getPlayers().indexOf(playerName);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            log.log(Level.SEVERE, "In method definePlayer: ", e);
            throw e;
        }
        j = i - j +1;
        s = "" + playerName + "-" + j;
        log.fine("Method definePlayer");
        return s;
    }
}
