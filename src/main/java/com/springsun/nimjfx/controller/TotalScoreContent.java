package com.springsun.nimjfx.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class TotalScoreContent {
    private static Logger log = Logger.getLogger(TotalScoreContent.class.getName());

    public static String getContentAsString(){
        Path path = null;
        try {
            URL url = TotalScoreContent.class.getResource("../TotalScore.txt");
            String pathAsString = GetOsIndependentPathToFile.getPath(url.toString());
            path = Paths.get(pathAsString);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in TotalScoreContent while trying to get path from url: ", e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
            stream.forEach(s -> stringBuilder.append(s).append("\n"));
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in TotalScoreContent " +
                    "at try-with-resources (building the string): ", e);
        }
        log.fine("in TotalScoreContent");
        return stringBuilder.toString();
    }

}
