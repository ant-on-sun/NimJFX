package com.springsun.nimjfx.controller;

import javafx.collections.ObservableList;

import java.util.logging.Logger;

public class ViktoryCondition {
    private static Logger log = Logger.getLogger(ViktoryCondition.class.getName());

    public static boolean nobodyHasWonYet(ObservableList<Integer> heaps){
        int sum = 0;
        for (int i = 0; i < heaps.size(); i++) {
            sum += (int)heaps.get(i);
        }
        if (sum == 0) {
            log.fine("Somebody has won, there are no stones in the HEAPS");
            return false;

        }
        log.fine("Nobody has won yet, there are some stones in the HEAPS");
        return true;
    }
}
