package com.schumakerteam.alpha.log;

public final class LogEntry {

    private LogTypeEnum type;
    private String message;

    public LogEntry(LogTypeEnum type, String message) {
        this.type = type;
        this.message = message;
    }

    public LogTypeEnum getType() {
        return type;
    }

    public void setType(LogTypeEnum type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }
}
