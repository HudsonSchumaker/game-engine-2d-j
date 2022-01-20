package com.schumakerteam.alpha;

import com.schumakerteam.alpha.core.Game;
import com.schumakerteam.alpha.log.LogService;

public class Main {

    public static void main(String ...args) {
        Game game = new Game(1024, 768);


        game.start();
    }
}
