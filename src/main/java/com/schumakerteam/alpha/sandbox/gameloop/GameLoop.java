package com.schumakerteam.alpha.sandbox.gameloop;

import java.security.SecureRandom;

/**
 * Abstract class for GameLoop implementation class.
 */
public abstract class GameLoop {

    protected volatile GameStatus status;
    protected final GameController controller;
    private Thread gameThread;

    /**
     * Initialize game status to be stopped.
     */
    public GameLoop() {
        controller = new GameController();
        status = GameStatus.STOPPED;
    }

    /**
     * Run game loop.
     */
    public void run() {
        status = GameStatus.RUNNING;
        gameThread = new Thread(this::processGameLoop);
        gameThread.start();
    }

    /**
     * Stop game loop.
     */
    public void stop() {
        status = GameStatus.STOPPED;
    }

    /**
     * Check if game is running or not.
     *
     * @return {@code true} if the game is running.
     */
    public boolean isGameRunning() {
        return status == GameStatus.RUNNING;
    }

    /**
     * Handle any user input that has happened since the last call. In order to
     * simulate the situation in real-life game, here we add a random time lag.
     * The time lag ranges from 50 ms to 250 ms.
     */
    protected void processInput() {
        try {
            var lag = new SecureRandom().nextInt(200) + 50;
            Thread.sleep(lag);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Render game frames to screen. Here we print bullet position to simulate
     * this process.
     */
    protected void render() {
        var position = controller.getBulletPosition();
        System.out.println("Current bullet position: " + position);
    }

    /**
     * execute game loop logic.
     */
    protected abstract void processGameLoop();
}