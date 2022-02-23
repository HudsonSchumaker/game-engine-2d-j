package com.schumakerteam.alpha.sandbox.gameloop;

public class HsGameLoop extends GameLoop {

    @Override
    protected void processGameLoop() {
        final int MAX_FRAMES_PER_SECOND = 144; // FPS
        final int MAX_UPDATES_SECOND = 75; // UPS

        final double uOPTIMAL_TIME = 1000.0 / MAX_UPDATES_SECOND;
        final double fOPTIMAL_TIME = 1000.0 / MAX_FRAMES_PER_SECOND;

        float uDeltaTime = 0.0f;
        float fDeltaTime = 0.0f;

        int frames = 0;
        int updates = 0;

        long startTime = System.currentTimeMillis();
        long timer = System.currentTimeMillis();

        while (isGameRunning()) {
            long currentTime = System.currentTimeMillis();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;

            this.processInput();

            if (uDeltaTime >= uOPTIMAL_TIME) {
                this.update(uDeltaTime / 60.f); // Create the deltaTime like Unity
                uDeltaTime -= uOPTIMAL_TIME;
            }

            if (fDeltaTime >= fOPTIMAL_TIME) {
                this.render();
                fDeltaTime -= fOPTIMAL_TIME;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    protected void update(float deltaTime) {
        controller.moveBullet(0.5f * deltaTime);
    }



    /*@Override
    public void run() {
        initialize();
        setup();

        final int MAX_FRAMES_PER_SECOND = 144; // FPS
        final int MAX_UPDATES_SECOND = 75; // UPS

        final double uOPTIMAL_TIME = 1000.0 / MAX_UPDATES_SECOND;
        final double fOPTIMAL_TIME = 1000.0 / MAX_FRAMES_PER_SECOND;

        double uDeltaTime = 0.0;
        double fDeltaTime = 0.0;

        int frames = 0;
        int updates = 0;

        long startTime = System.currentTimeMillis();
        long timer = System.currentTimeMillis();

        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            uDeltaTime += (currentTime - startTime);
            fDeltaTime += (currentTime - startTime);
            startTime = currentTime;

            this.processInput();

            if (uDeltaTime >= uOPTIMAL_TIME) {
                this.update(uDeltaTime / 60); // Create the deltaTime like Unity

                updates++;
                uDeltaTime -= uOPTIMAL_TIME;
            }

            if (fDeltaTime >= fOPTIMAL_TIME) {
                this.render();
                frames++;
                fDeltaTime -= fOPTIMAL_TIME;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                windowGame.setTitle("FPS: " + frames + " UPS: " + updates);
                this.FPS = frames;
                this.UPS = updates;
                frames = 0;
                updates = 0;
            }
        }
    }*/
}
