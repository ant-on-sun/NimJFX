package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.ListOfHeaps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

import static org.junit.Assert.*;

public class MadComputerMoveTest {
    ListOfHeaps hspy;

    @Before
    public void setUp() throws Exception {
        hspy = spy(new ListOfHeaps(4, 10));
    }

    @After
    public void tearDown() throws Exception {
        hspy = null;
    }

    @Test
    public void moveMadComputerTest1() throws Exception {
        MadComputerMove.moveMadComputer(hspy);
        Mockito.verify(hspy, times(1)).setListOfHeaps(anyInt(), anyInt());
    }

}