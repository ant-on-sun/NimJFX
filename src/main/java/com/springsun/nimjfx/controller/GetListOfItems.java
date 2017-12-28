package com.springsun.nimjfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.logging.Logger;

public class GetListOfItems {
    private static Logger log = Logger.getLogger(GetListOfItems.class.getName());

    public static ObservableList<Integer> getList(Integer stones){
        ObservableList<Integer> listOfItems = FXCollections.observableArrayList();
        for (int i = 0; i <= stones; i++){
            listOfItems.add(i, i);
        }
        log.fine("In GetListOfItems getList()");
        return listOfItems;
    }
}
