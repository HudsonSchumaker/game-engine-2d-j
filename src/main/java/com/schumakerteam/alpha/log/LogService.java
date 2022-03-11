package com.schumakerteam.alpha.log;

import java.util.concurrent.*;

/**
 * @author Hudson Schumaker
 */
public final class LogService implements ILogService {

    private static final LogService INSTANCE = new LogService();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static final int CORE_POOL_SIZE = 1;
    private static final int MAXIMUM_POOL_SIZE = 1;
    private static final long KEEP_ALIVE_TIME = 5;

    private final BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
    private final ExecutorService executor =
            new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, blockingQueue);

    private LogService() {}

    public static LogService getInstance() {
        return INSTANCE;
    }

    @Override
    public void info(String message) {
        var log = new LogEntry(LogTypeEnum.LOG_INFO, ANSI_GREEN + "LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
        executor.submit(log);
    }

    @Override
    public void warning(String message) {
        var log = new LogEntry(LogTypeEnum.LOG_WARNING, ANSI_YELLOW + "LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
        executor.submit(log);
    }

    @Override
    public void error(String message) {
        var log = new LogEntry(LogTypeEnum.LOG_ERROR, ANSI_RED + "LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
        executor.submit(log);
    }

    @Override
    public void engine(String message) {
        var log = new LogEntry(LogTypeEnum.LOG_ENGINE, ANSI_BLUE + "LOG [" + DateUtil.getLogDate() + "] " + message + ANSI_RESET);
        executor.submit(log);
    }
}
