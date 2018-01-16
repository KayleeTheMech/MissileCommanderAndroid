package org.kittyinboots.missilecommanderandroid.core.gameObjects;


import org.kittyinboots.missilecommanderandroid.core.Position;

public interface IFlightObject extends IGameObject {

    Position getTargetVector();

    void setTargetVector(Position tar);

}
