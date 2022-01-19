package com.schumakerteam.alpha.sandbox;

import com.schumakerteam.alpha.ecs.impl.Signature;

public class TestBitset {

    public static void main(String ...args) {

        Signature system = new Signature();
        system.set(2);
        system.set(1);


        if (system.get(0)) {
            System.out.println("verdadeiro");
        }


        Signature entity = new Signature();
        entity.set(6);
        entity.set(8);
        entity.set(2);

        var s = entity.toString();
        var b = entity.toString().contains(system.toString());
        var z = entity.intersects(system);
        var x = system.intersects(entity);
    }
}
