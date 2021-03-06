package com.schumakerteam.alpha.common;

import java.util.concurrent.ThreadFactory;

/**
 * @author Hudson Schumaker
 * Schumaker Labs
 */
public final class GeThreadFactory implements ThreadFactory {

    public static long number = 0L;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, "ge_" + number);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.setUncaughtExceptionHandler(new GeExceptionHandler());
        number++;
        return thread;
    }
}
