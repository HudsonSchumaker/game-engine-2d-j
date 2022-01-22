package com.schumakerteam.alpha.ecs.impl;

import java.util.BitSet;

public final class Signature extends BitSet {

    private static final int N_BITS = 16;

    public Signature() {
        super(N_BITS);
    }

    // TODO check if is better not be a static method.
    public static boolean contains(BitSet a, BitSet b) {
        BitSet aClone = (BitSet) a.clone();
        aClone.and(b);
        return aClone.equals(b);
    }
}
