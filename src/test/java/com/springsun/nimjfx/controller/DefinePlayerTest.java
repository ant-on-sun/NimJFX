package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.ListOfPlayers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.*;

public class DefinePlayerTest {
    ListOfPlayers p;

    @Before
    public void setUp() throws Exception {
        p = new ListOfPlayers(1,2,1);
    }

    @After
    public void tearDown() throws Exception {
        p = null;
    }

    @Test
    public void definePlayerTest1() throws Exception {
        String s = "HUMAN-1";
        assertEquals(s, DefinePlayer.definePlayer(p, 0));
    }

    @Test
    public void definePlayerTest2() throws Exception {
        String s = "COMPUTER-2";
        assertEquals(s, DefinePlayer.definePlayer(p, 2));
    }

    @Test
    public void definePlayerTest3() throws Exception {
        String s = "MAD_COMPUTER-1";
        assertEquals(s, DefinePlayer.definePlayer(p, 3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void definePlayerTest4() throws Exception {
        DefinePlayer.definePlayer(p, 4);
    }

    @Test(expected = NullPointerException.class)
    public void definePlayerTest5() throws Exception {
        p = null;
        DefinePlayer.definePlayer(p, 0);
    }

}