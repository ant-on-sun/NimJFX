package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.ListOfHeaps;
import javafx.collections.ObservableList;

import java.util.logging.Logger;

public class ComputerMove {
    private static Logger log = Logger.getLogger(ComputerMove.class.getName());

    public static void moveComputer(ListOfHeaps h) {
        ObservableList<Integer> heaps = h.getListOfHeaps();
        int nimSum = 0;
        for (int i = 0; i < heaps.size(); i++) {
            nimSum ^= heaps.get(i);
        }
        if (nimSum != 0){
            for (int i = 0; i < heaps.size(); i++) {
                int j = nimSum^heaps.get(i);
                if (j < heaps.get(i)) {
                    h.setListOfHeaps(i,j);
                    break;
                }
            }
        } else {
            MadComputerMove.moveMadComputer(h);
            log.info("Computer's move. The computer did not find a suitable move and delegated its execution" +
                    " to Mad Computer");
        }
        log.fine("Computer's move");
    }
}
