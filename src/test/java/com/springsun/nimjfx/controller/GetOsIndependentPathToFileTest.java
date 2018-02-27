package com.springsun.nimjfx.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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

    //in Windows
    @Test @Ignore
    public void getPathTest1() throws Exception {
        if (System.getProperty("os.name").contains("indow")){
            s = "file:/C:\\Users";
            assertEquals("C:\\Users", GetOsIndependentPathToFile.getPath(s));
        }

    }

    //in Windows
    @Test @Ignore
    public void getPathTest2() throws Exception {
        if (System.getProperty("os.name").contains("indow")){
            s = "file:/E:\\Users";
            assertEquals("E:\\Users", GetOsIndependentPathToFile.getPath(s));
        }

    }

    //in Windows
    @Test @Ignore //(expected = StringIndexOutOfBoundsException.class)
    public void getPathTest3() throws Exception {
        if (System.getProperty("os.name").contains("indow")){
            s = ":\\Users";
            GetOsIndependentPathToFile.getPath(s);
        }

    }

    //in Unix
    @Test (expected = StringIndexOutOfBoundsException.class)
    public void getPathTest4(){
        if (!System.getProperty("os.name").contains("indow")){
            s = "someDirectory/anotherDirectory";
            String result = GetOsIndependentPathToFile.getPath(s);
            System.out.println("result = " + result);
        }
    }

}