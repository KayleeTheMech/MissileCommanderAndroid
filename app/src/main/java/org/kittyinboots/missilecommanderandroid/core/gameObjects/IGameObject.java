package org.kittyinboots.missilecommanderandroid.core.gameObjects;


import org.kittyinboots.missilecommanderandroid.core.Position;

public interface IGameObject {

    boolean isAlive();

    void kill();

    Position getPosition();

    void setPosition(Position r);
}
