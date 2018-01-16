package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.Players;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateScoreFile {
    private static Logger log = Logger.getLogger(CreateScoreFile.class.getName());
    private static String pathAsString;
    private static String pathDirectory;

    public static Boolean createFile(){
        pathDirectory = System.getProperty("user.home") + File.separator + "NimJFX" + File.separator;
        pathAsString = pathDirectory + "TotalScore.txt";
        try {
            Files.createDirectories(Paths.get(pathDirectory));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception while creating directories in path: " + pathDirectory, e);
        }
        if (new File(pathAsString).exists()) return true;
        List<String> lines = Arrays.asList(Players.HUMAN.toString() + ": 0", Players.COMPUTER.toString() +
                ": 0", Players.MAD_COMPUTER.toString() + ": 0");
        try {
            Files.write(Paths.get(pathAsString), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception while creating file TotalScore.txt in path: " + pathAsString, e);
        }
        if (new File(pathAsString).exists()) return true;
        return false;
    }

    public static String getPathAsString() {
        return pathAsString;
    }
}
