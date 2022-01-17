package com.schumakerteam.alpha.log;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static String getLogDate() {
       try {
           return FORMATTER.format(new Date().toInstant());
       } catch (Exception e) {
           System.err.println(e.getMessage());
           return " - - - ";
       }
    }
}
