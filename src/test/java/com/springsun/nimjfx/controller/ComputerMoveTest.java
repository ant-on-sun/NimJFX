package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.ListOfHeaps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MadComputerMove.class)
public class ComputerMoveTest {
    ListOfHeaps hspy;

    @Before
    public void setUp() throws Exception {
        hspy = spy(new ListOfHeaps(3, 10));
        PowerMockito.mockStatic(MadComputerMove.class);
    }

    @After
    public void tearDown() throws Exception {
        hspy = null;
    }

    @Test
    public void moveComputerTest1() throws Exception {
        ComputerMove.moveComputer(hspy);
        Mockito.verify(hspy, times(1)).setListOfHeaps(0, 0);
    }

    @Test
    public void moveComputerTest2() throws Exception {
        hspy = spy(new ListOfHeaps(4, 2));
        ComputerMove.moveComputer(hspy);
        PowerMockito.verifyStatic(MadComputerMove.class, times(1));
        MadComputerMove.moveMadComputer(hspy);
    }

}