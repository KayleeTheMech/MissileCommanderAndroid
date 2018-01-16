package org.kittyinboots.missilecommanderandroid.core.gameObjects;


import org.kittyinboots.missilecommanderandroid.core.Position;

public abstract class FlightObject extends GameObject implements IFlightObject {

    protected Position flightVector;
    protected Position target;
    protected int speed;

    @Override
    public Position getPosition() {
        flightVector = target.subtract(position);
        double norm = flightVector.getLength();
        return position.add(flightVector.multiply(clock * speed / norm));
    }

    @Override
    public Position getTargetVector() {
        return target;
    }

    @Override
    public void setTargetVector(Position target) {
        this.target = target;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
