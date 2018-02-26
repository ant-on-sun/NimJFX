package com.springsun.nimjfx.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class TotalScoreContent {
    private static Logger log = Logger.getLogger(TotalScoreContent.class.getName());

    public static String getContentAsString() {
        Path path = null;
        String pathAsString = "";
        //File file = null;
        try {
            CreateScoreFile.createFile();
            pathAsString = CreateScoreFile.getPathAsString();
//            file = new File(pathAsString);
//            if (!file.exists()) {
//                CreateScoreFile.createFile();
//            }
            path = Paths.get(pathAsString);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in TotalScoreContent while trying to get path from url: ", e);
            log.log(Level.SEVERE, "pathAsString: ", pathAsString);
        }
        /*Building string from file*/
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s).append("\n"));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in TotalScoreContent " +
                    "at try-with-resources (building the string): ", e);
        }
        log.fine("in TotalScoreContent");
        return stringBuilder.toString();
    }

}
