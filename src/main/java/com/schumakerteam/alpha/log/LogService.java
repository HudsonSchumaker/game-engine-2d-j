package com.schumakerteam.alpha.log;

import java.util.ArrayList;
import java.util.List;

public class LogService implements ILogService {

    private static final LogService INSTANCE = new LogService();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private List<LogEntry> messages = new ArrayList();

    private LogService() {}

    public static LogService getInstance() {
        return INSTANCE;
    }

    @Override
    public void info(String message) {
        new Thread(() -> {
            var log = new LogEntry(LogTypeEnum.LOG_INFO, ANSI_GREEN + "LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
            System.out.println(log.getMessage());
            messages.add(log);
        }).start();
    }

    @Override
    public void warning(String message) {
        new Thread(() -> {
            var log = new LogEntry(LogTypeEnum.LOG_WARNING, ANSI_YELLOW +"LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
            System.out.println(log.getMessage());
            messages.add(log);
        }).start();
    }

    @Override
    public void error(String message) {
        new Thread(() -> {
            var log = new LogEntry(LogTypeEnum.LOG_ERROR, ANSI_RED + "LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
            System.out.println(log.getMessage());
            messages.add(log);
        }).start();
    }

    @Override
    public void engine(String message) {
        new Thread(() -> {
            var log = new LogEntry(LogTypeEnum.LOG_ENGINE, ANSI_BLUE + "LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
            System.out.println(log.getMessage());
            messages.add(log);
        }).start();
    }
}
