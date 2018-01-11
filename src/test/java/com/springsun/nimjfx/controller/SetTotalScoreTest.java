package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.Players;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class SetTotalScoreTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setScoreTest1() throws Exception {
        String player = Players.HUMAN.toString();
        String strAfter = SetTotalScore.setScore(player);
        String strBefore = SetTotalScore.getStringBuilderBeforeChange().toString();
        assertNotEquals(strBefore, strAfter);
        assertEquals(parserInt(strBefore, player) + 1, parserInt(strAfter, player));
    }

    @Test
    public void setScoreTest2() throws Exception {
        String player = Players.COMPUTER.toString();
        String strAfter = SetTotalScore.setScore(player);
        String strBefore = SetTotalScore.getStringBuilderBeforeChange().toString();
        assertNotEquals(strBefore, strAfter);
        assertEquals(parserInt(strBefore, player) + 1, parserInt(strAfter, player));
    }

    @Test
    public void setScoreTest3() throws Exception {
        String player = Players.MAD_COMPUTER.toString();
        String strAfter = SetTotalScore.setScore(player);
        String strBefore = SetTotalScore.getStringBuilderBeforeChange().toString();
        assertNotEquals(strBefore, strAfter);
        assertEquals(parserInt(strBefore, player) + 1, parserInt(strAfter, player));
    }

    private int parserInt(String score, String player){
        int result = 0;
        String str;
        Pattern pat = Pattern.compile("\\d+");
        String[] strArr = score.split("\n");
        for (int i = 0; i < strArr.length; i++){
            if (strArr[i].startsWith(player)){
                Matcher matcher = pat.matcher(strArr[i]);
                int start = 0;
                while (matcher.find(start)){
                    str = strArr[i].substring(matcher.start(), matcher.end());
                    result = Integer.parseInt(str);
                    start = matcher.end();
                }
            }
        }
        return result;
    }

}