package com.springsun.nimjfx.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

public class ListOfHeapsTest {
    ListOfHeaps listOfHeaps;

    @Before
    public void setUp() throws Exception {
        listOfHeaps = new ListOfHeaps(3, 4);
    }

    @After
    public void tearDown() throws Exception {
        listOfHeaps = null;
    }

    @Test
    public void getListOfHeapsTest1() throws Exception {
        assertEquals(4, (int)listOfHeaps.getListOfHeaps().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getListOfHeapsTest2() throws Exception {
        assertEquals(4, (int)listOfHeaps.getListOfHeaps().get(4));
    }

    @Test
    public void setListOfHeapsTest1() throws Exception {
        listOfHeaps.setListOfHeaps(0, 3);
        assertEquals(3, (int)listOfHeaps.getListOfHeaps().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setListOfHeapsTest2() throws Exception {
        listOfHeaps.setListOfHeaps(4, 3);
    }

}