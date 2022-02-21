package com.schumakerteam.alpha.geometry;

public final class Vector2D {

    private double x;
    private double y;

    public Vector2D() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D Forward() {
        return new Vector2D(1, 0);
    }

    public static Vector2D Backward() {
        return new Vector2D(-1, 0);
    }

    public static Vector2D Up() {
        return new Vector2D(0, -1);
    }

    public static Vector2D Down() {
        return new Vector2D(0, 1);
    }

    public static Vector2D Zero() { return new Vector2D(); }

    public static Vector2D one() { return new Vector2D(1, 1); }

    public static Vector2D offset() { return Vector2D.one(); }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2D{" + "x=" + x + ", y=" + y + '}';
    }
}
