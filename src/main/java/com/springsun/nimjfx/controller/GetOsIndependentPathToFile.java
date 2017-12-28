package com.springsun.nimjfx.controller;

import java.util.logging.Logger;

public class GetOsIndependentPathToFile {
    private static Logger log = Logger.getLogger(GetOsIndependentPathToFile.class.getName());

    public static String getPath (String s){
        if (s.startsWith("file:/")) s = s.substring(6);
        if (System.getProperty("os.name").contains("indow")){
            while (!s.startsWith("C")){
                s = s.substring(1);
            }
            log.fine("GetOsIndependentPathToFile getPath() it's Windows, baby");
            return s;
        }
        log.fine("GetOsIndependentPathToFile getPath() it's not Windows, baby");
        return s;
    }

}
