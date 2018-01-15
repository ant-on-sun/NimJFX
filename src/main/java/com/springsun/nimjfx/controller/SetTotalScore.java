package com.springsun.nimjfx.controller;

import com.springsun.nimjfx.model.Players;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SetTotalScore {
    private static Logger log = Logger.getLogger(SetTotalScore.class.getName());
    private static StringBuilder stringBuilderBeforeChange = new StringBuilder();

    public static String setScore(String winnerName){
        String pathAsString = null;
        Path path = null;
        File file = null;
        try {
            URL url = SetTotalScore.class.getResource("/TotalScore.txt");
            pathAsString = GetOsIndependentPathToFile.getPath(url.toString());
            file = new File(pathAsString);
            if (!file.exists()) {
                List<String> lines = Arrays.asList(Players.HUMAN.toString() + ": 0", Players.COMPUTER.toString() +
                        ": 0", Players.MAD_COMPUTER.toString() + ": 0");
                Files.write(Paths.get(pathAsString), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
            path = Paths.get(pathAsString);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception caught in SetTotalScore while trying to get path from url: ", e);
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)){
            stream.forEach((s) -> {
                String line = s.toString();
                stringBuilderBeforeChange.append(line).append("\n");
                if (line.startsWith(winnerName)){
                    String str = "";
                    int result = 0;
                    Pattern pat = Pattern.compile("\\d+");
                    Matcher matcher = pat.matcher(line);
                    int start = 0;
                    while (matcher.find(start)){
                        str = line.substring(matcher.start(), matcher.end());
                        result = Integer.parseInt(str);
                        start = matcher.end();
                        result++;
                    }
                    int i = line.indexOf(" ");
                    str = line.substring(0, i+1);
                    stringBuilder.append(str).append("" + result).append("\n");
                } else {
                    stringBuilder.append(line).append("\n");
                }

            });
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in SetTotalScore " +
                    "at try-with-resources (building the string): ", e);
        }
        try {
            new PrintWriter(pathAsString).close();
        } catch (FileNotFoundException e) {
            log.log(Level.SEVERE, "Exception caught in SetTotalScore at PrintWriter (clearing file): ", e);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write(stringBuilder.toString());
//            writer.write( Players.HUMAN.toString() + ": 0\n" + Players.COMPUTER.toString() + ": 0\n" +
//                   Players.MAD_COMPUTER.toString() + ": 0\n");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exception caught in SetTotalScore at try-with-resources (writing in file): ", e);
        }
        return stringBuilder.toString();
    }

    public static StringBuilder getStringBuilderBeforeChange() {
        return stringBuilderBeforeChange;
    }

}
