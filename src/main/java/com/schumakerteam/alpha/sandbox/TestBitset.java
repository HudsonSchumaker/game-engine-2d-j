package com.schumakerteam.alpha.sandbox;

import com.schumakerteam.alpha.ecs.Signature;

public class TestBitset {

    public static void main(String ...args) {

        Signature bitset = new Signature();
        bitset.set(0);
        bitset.set(1, false);

        if (bitset.get(0)) {
            System.out.println("verdadeiro");
        }

        System.out.println(bitset.get(1));

    }
}
