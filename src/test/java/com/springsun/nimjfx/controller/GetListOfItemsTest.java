package com.springsun.nimjfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetListOfItemsTest {
    ObservableList<Integer> listOfItems = FXCollections.observableArrayList();

    @Before
    public void setUp() throws Exception {
        listOfItems = GetListOfItems.getList(3);
    }

    @After
    public void tearDown() throws Exception {
        listOfItems = null;
    }

    @Test
    public void getList() throws Exception {
        assertEquals(2, (int)listOfItems.get(2));
    }

}