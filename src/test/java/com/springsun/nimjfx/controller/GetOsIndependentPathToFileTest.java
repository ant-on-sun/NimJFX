package com.springsun.nimjfx.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetOsIndependentPathToFileTest {
    String s;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPathTest1() throws Exception {
        s = "file:/C:\\Users";
        assertEquals("C:\\Users", GetOsIndependentPathToFile.getPath(s));
    }

    @Test
    public void getPathTest2() throws Exception {
        s = "file:/E:\\Users";
        assertEquals("E:\\Users", GetOsIndependentPathToFile.getPath(s));
    }

    @Test (expected = StringIndexOutOfBoundsException.class)
    public void getPathTest3() throws Exception {
        s = "";
        GetOsIndependentPathToFile.getPath(s);
    }

}