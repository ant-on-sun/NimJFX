package com.springsun.nimjfx.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

public class ListOfPlayersTest {
    ListOfPlayers players;

    @Before
    public void setUp() throws Exception {
        players = new ListOfPlayers(1, 2, 1);
    }

    @After
    public void tearDown() throws Exception {
        players = null;
    }

    @Test
    public void getPlayersTest1() throws Exception {
        assertEquals(Players.HUMAN, players.getPlayers().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getPlayersTest2() throws Exception {
        players.getPlayers().get(4);
    }

}