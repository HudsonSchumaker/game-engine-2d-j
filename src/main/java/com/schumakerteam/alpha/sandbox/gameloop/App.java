package com.schumakerteam.alpha.sandbox.gameloop;

public class App {
    /**
     * Each type of game loop will run for 2 seconds.
     */
    private static final int GAME_LOOP_DURATION_TIME = 2000;

    public static void main(String[] args) {
        try {
            System.out.println("Start frame-based game loop:");
            var frameBasedGameLoop = new FrameBasedGameLoop();
            frameBasedGameLoop.run();
            Thread.sleep(GAME_LOOP_DURATION_TIME);
            frameBasedGameLoop.stop();
            System.out.println("Stop frame-based game loop.");

            System.out.println("Start variable-step game loop:");
            var variableStepGameLoop = new VariableStepGameLoop();
            variableStepGameLoop.run();
            Thread.sleep(GAME_LOOP_DURATION_TIME);
            variableStepGameLoop.stop();
            System.out.println("Stop variable-step game loop.");

            System.out.println("Start fixed-step game loop:");
            var fixedStepGameLoop = new FixedStepGameLoop();
            fixedStepGameLoop.run();
            Thread.sleep(GAME_LOOP_DURATION_TIME);
            fixedStepGameLoop.stop();
            System.out.println("Stop variable-step game loop.");

            System.out.println("Start Hs game loop:");
            var hsGameLoop = new HsGameLoop();
            hsGameLoop.run();
            Thread.sleep(GAME_LOOP_DURATION_TIME);
            hsGameLoop.stop();
            System.out.println("Stop Hs game loop.");

        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}