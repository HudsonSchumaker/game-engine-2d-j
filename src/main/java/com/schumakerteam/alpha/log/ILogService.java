package com.schumakerteam.alpha.log;

public interface ILogService {
    void info(String message);
    void warning(String message);
    void error(String message);
    void engine(String message);
}
