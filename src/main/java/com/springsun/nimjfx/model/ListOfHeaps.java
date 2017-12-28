package com.springsun.nimjfx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.logging.Logger;

public class ListOfHeaps {
    private static Logger log = Logger.getLogger(ListOfHeaps.class.getName());
    private ObservableList<Integer> listOfHeaps;

    public ListOfHeaps(final int numberOfHeaps, final int numberOfStones) {
        ObservableList<Integer> listOfHeaps = FXCollections.observableArrayList();
        for (int i = 0; i < numberOfHeaps; i++) {
            listOfHeaps.add(i, numberOfStones);
        }
        this.listOfHeaps = listOfHeaps;
        log.fine("Filling list of heaps");
    }

    public static class Builder {
        private int numberOfHeaps;
        private int numberOfStones;

        public Builder numberOfHeaps(int numberOfHeaps) {
            this.numberOfHeaps = numberOfHeaps;
            return this;
        }

        public Builder numberOfStones(int numberOfStones) {
            this.numberOfStones = numberOfStones;
            return this;
        }

        public ListOfHeaps createListOfHeaps() {
            return new ListOfHeaps(numberOfHeaps, numberOfStones);
        }
    }

    public ObservableList<Integer> getListOfHeaps() {
        return listOfHeaps;
    }

    public void setListOfHeaps(int numberOfHeap, int numberOfStones) {
        listOfHeaps.set(numberOfHeap, numberOfStones);
    }

}
