package com.springsun.nimjfx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

public class ViktoryConditionTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void nobodyHasWonYetTest1() throws Exception {
        ObservableList<Integer> heaps = FXCollections.observableArrayList(0, 1, 0);
        assertTrue(ViktoryCondition.nobodyHasWonYet(heaps));
    }

    @Test
    public void nobodyHasWonYetTest2() throws Exception {
        ObservableList<Integer> heaps = FXCollections.observableArrayList(0, 0, 0);
        assertFalse(ViktoryCondition.nobodyHasWonYet(heaps));
    }

}