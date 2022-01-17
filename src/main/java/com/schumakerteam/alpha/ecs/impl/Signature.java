package com.schumakerteam.alpha.ecs.impl;

import java.util.BitSet;

public class Signature extends BitSet {

    private static final int N_BITS = 16;

    public Signature() {
        super(N_BITS);
    }
}
