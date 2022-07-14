package com.schumakerteam.alpha.common;

/**
 * @author Hudson Schumaker
 * Schumaker Labs
 */
public final class GeExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Exception on thread " + t.getName() + ": " + e.getMessage());
    }
}
