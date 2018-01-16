package org.kittyinboots.missilecommanderandroid.core.gameObjects;


import org.kittyinboots.missilecommanderandroid.core.Position;

public class Missile extends FlightObject {

    private static final int SPEED = 50;

    @Override
    public Position getPosition() {
        double currentSpeed = (SPEED * Math.exp(-(20 / (clock + 0.0000000001))));
        flightVector = target.subtract(position);
        double alpha = (currentSpeed * clock / flightVector.getLength());
        flightVector = flightVector.multiply(alpha);
        return position.add(flightVector);
    }

    public boolean withinRange(Position position) {
        return position.subtract(getPosition()).getLength() <= detonationRadius;
    }

    public boolean reachedTarget() {
        return getPosition().subtract(this.position).getLength() >= getTargetVector().subtract(this.position).getLength();
    }

}
