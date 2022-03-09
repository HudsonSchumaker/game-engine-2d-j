package com.schumakerteam.alpha.ecs.impl;

import java.util.BitSet;

/**
 * @author Hudson Schumaker
 */
public final class Signature extends BitSet {

    private static final int N_BITS = 16;

    public Signature() {
        super(N_BITS);
    }
    
    public static boolean contains(BitSet a, BitSet b) {
        BitSet aClone = (BitSet) a.clone();
        aClone.and(b);
        return aClone.equals(b);
    }
}
