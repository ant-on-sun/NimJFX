package com.springsun.nimjfx.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageShow {
    private static Logger log = Logger.getLogger(MessageShow.class.getName());
    private static Label message;

    public static void showText(Label label, String text){
        message = label;
        Thread messageThred = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    log.log(Level.SEVERE, "Exception caught in MessageShow showText() Thread.sleep: ", e);
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        message.setText(text);
                    }
                });
            }
        });
        messageThred.start();
        log.fine("MessageShow showText()");
    }

}
