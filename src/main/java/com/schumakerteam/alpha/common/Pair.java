package com.schumakerteam.alpha.common;

public class Pair<L, R> {
    private final L left;
    private final R right;

    private Pair(L l, R r) {
        this.left = l;
        this.right = r;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    public L getLeft() {
        return this.left;
    }

    public R getRight() {
        return this.right;
    }
}
