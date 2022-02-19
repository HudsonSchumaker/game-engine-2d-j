package com.schumakerteam.alpha.log;

public final class LogEntry implements Runnable {

    private final LogTypeEnum type;
    private final String message;

    public LogEntry(LogTypeEnum type, String message) {
        this.type = type;
        this.message = message;
    }

    public LogTypeEnum getType() {
        return type;
    }

    @Override
    public void run() {
        System.out.println(message);
    }
}
