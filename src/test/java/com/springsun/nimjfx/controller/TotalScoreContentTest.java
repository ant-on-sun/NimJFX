package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.Players;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TotalScoreContentTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContentAsString() throws Exception {
        String content = TotalScoreContent.getContentAsString();
        assert(content.startsWith(Players.HUMAN.toString()));
        assert(content.contains(Players.COMPUTER.toString()));
        assert(content.contains(Players.MAD_COMPUTER.toString()));
    }

}