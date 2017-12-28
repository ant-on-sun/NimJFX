package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.ListOfHeaps;
import javafx.collections.ObservableList;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class MadComputerMove {
    private static Logger log = Logger.getLogger(MadComputerMove.class.getName());

    public static void moveMadComputer(ListOfHeaps h) {
        ObservableList<Integer> heaps = h.getListOfHeaps();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int randomNumHeap;
        do {
            randomNumHeap = ThreadLocalRandom.current().nextInt(0, heaps.size());
        } while (heaps.get(randomNumHeap) == 0);
        int randomNumStones = ThreadLocalRandom.current().nextInt(1,heaps.get(randomNumHeap) + 1);
        h.setListOfHeaps(randomNumHeap,heaps.get(randomNumHeap) - randomNumStones);
        log.fine("Mad computer's move");
    }
}
