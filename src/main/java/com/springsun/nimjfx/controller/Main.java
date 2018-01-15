package com.springsun.nimjfx.controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main extends Application {

    static {
        InputStream stream = Main.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            System.err.println("Could not set up logger configuration: " + e.toString());
        }
    }

    private static Stage primaryStage;
    private static Logger log = Logger.getLogger(Main.class.getName());

    @Override
    public void init(){
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FirstScene.fxml"));
        this.primaryStage.setTitle("Nim Game");
        this.primaryStage.setMinHeight(490);
        this.primaryStage.setMinWidth(700);
        Scene scene = new Scene(root, 700, 500);
        this.primaryStage.setScene(scene);
        this.primaryStage.setOnCloseRequest(event -> {
            event.consume();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/NoticeExitInTheEnd.fxml"));
            try {
                Parent rootClose = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(this.primaryStage.getScene().getWindow());
                stage.setTitle("Notice");
                stage.setScene(new Scene(rootClose));
                stage.show();
            } catch (IOException e) {
                log.log(Level.SEVERE, "Exception caught in Main start() setOnCloseRequest: ", e);
            }
            }
        );
        this.primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in Main: ", e);
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}
