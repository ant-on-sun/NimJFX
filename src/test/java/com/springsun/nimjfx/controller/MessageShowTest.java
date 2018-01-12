package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.view.appView.MainSceneController;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class MessageShowTest extends ApplicationTest{

    @BeforeClass
    public static void setUpSpec() throws Exception {
        if (Boolean.getBoolean("headless")){
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }

    private String s = "Player moves";

    @Before
    public void setUp() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void showTextTest1() throws Exception {
        Stage stage = Main.getPrimaryStage();
        Label label;
        clickOn(stage.getScene().lookup(".start-button"));
        Label message = (Label) MainSceneController.getPane().getScene().lookup(".lable-text-one");
        MessageShow.showText(message, s);
        Thread.sleep(900);
        label = (Label) MainSceneController.getPane().getScene().lookup(".lable-text-one");
        assertEquals(s, label.textProperty().getValue());
    }

}