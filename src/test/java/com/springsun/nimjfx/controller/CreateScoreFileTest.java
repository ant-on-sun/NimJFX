package com.springsun.nimjfx.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateScoreFileTest {
    @Test
    public void createFileTest() throws Exception {
        Boolean fileExist = CreateScoreFile.createFile();
        assertTrue(fileExist);
    }

}