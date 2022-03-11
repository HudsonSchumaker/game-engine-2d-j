package com.schumakerteam.alpha.log;

/**
 * @author Hudson Schumaker
 */
public interface ILogService {
    void info(String message);
    void warning(String message);
    void error(String message);
    void engine(String message);
}
