package com.springsun.nimjfx.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class MessageShow {
    private static Label message;

    public static void showText(Label label, String text){
        message = label;
        Thread messageThred = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
    }

}
