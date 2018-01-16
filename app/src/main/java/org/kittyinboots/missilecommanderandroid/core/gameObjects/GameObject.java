package org.kittyinboots.missilecommanderandroid.core.gameObjects;


import org.kittyinboots.missilecommanderandroid.core.Position;

import java.util.Observable;
import java.util.Observer;

public abstract class GameObject implements IGameObject, Observer {

    protected int detonationRadius = 0;
    protected int clock = 0;
    protected Position position = null;
    private boolean alive = true;

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void kill() {
        alive = false;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        clock++;
    }

    public int getDetonationRadius() {
        return detonationRadius;
    }

    public void setDetonationRadius(int r) {
        this.detonationRadius = r;
    }

}
