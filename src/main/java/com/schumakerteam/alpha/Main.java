package com.schumakerteam.alpha;

import com.schumakerteam.alpha.core.impl.Game;

/**
 * @author Hudson Schumaker
 */
public class Main {

    public static void main(String ...args) {
        Game game = new Game(1600, 480);
        game.start();
    }
}
