package org.kittyinboots.missilecommanderandroid.core;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position add(Position pos) {
        return new Position(x + pos.getX(), y + pos.getY());
    }

    public Position subtract(Position pos) {
        return new Position(x - pos.getX(), y - pos.getY());
    }

    public Position multiply(double alpha) {
        return new Position((int) (x * alpha), (int) (y * alpha));
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
