package com.schumakerteam.alpha;

import com.schumakerteam.alpha.core.impl.Game;

public class Main {

    public static void main(String ...args) {
        Game game = new Game(1024, 768);

        game.start();
    }
}
