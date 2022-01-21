package com.schumakerteam.alpha.sandbox;

import java.util.Timer;
import java.util.TimerTask;

public class JxTimerTask {

    public static void main(String... args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 1, 10);
    }
}

class ScheduleTask extends TimerTask {

    @Override
    public void run() {
        final int MAX_FRAMES_PER_SECOND = 120; // FPS
        final int MAX_UPDATES_SECOND = 60; // UPS

        final double uOPTIMAL_TIME = 1000000000.0 / MAX_UPDATES_SECOND;
        final double fOPTIMAL_TIME = 1000000000.0 / MAX_FRAMES_PER_SECOND;

        double uDeltaTime = 0.0;
        double fDeltaTime = 0.0;

        int frames = 0;
        int updates = 0;

        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        for (;;) {
            long currentTime = System.nanoTime();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;

            if (uDeltaTime >= uOPTIMAL_TIME) {
                // update(uDeltaTime / 1000000000.0); // Create the deltaTime like Unity
                updates++;
                uDeltaTime -= uOPTIMAL_TIME;
            }

            if (fDeltaTime >= fOPTIMAL_TIME) {
                //render();
                frames++;
                fDeltaTime -= fOPTIMAL_TIME;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
            }

            //
        }
    }
}

