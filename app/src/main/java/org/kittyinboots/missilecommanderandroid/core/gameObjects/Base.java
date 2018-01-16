package org.kittyinboots.missilecommanderandroid.core.gameObjects;

public class Base extends GameObject {

    private int score = 0;

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

}
