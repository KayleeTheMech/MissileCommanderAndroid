package org.kittyinboots.missilecommanderandroid.core.gameObjects;


import org.kittyinboots.missilecommanderandroid.core.Position;

import java.util.Observable;
import java.util.Observer;

public class Explosion extends GameObject implements Observer {

    public static final double FRAME_RATE_SCALING = 1;
    public static final double EXPLOSION_DECAY_CONSTANT = 0.4;
    public static final double EXPLOSION_DETONATION_VELOCITY = 1.07;

    public boolean withinRange(Position abs) {
        Position rel = abs.subtract(this.position);
        return rel.getLength() < detonationRadius;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (clock > 5) {
            detonationRadius = (int) (detonationRadius - clock * FRAME_RATE_SCALING * EXPLOSION_DECAY_CONSTANT);
        } else if (clock > 0) {
            detonationRadius = (int) (detonationRadius + clock * FRAME_RATE_SCALING * EXPLOSION_DETONATION_VELOCITY);
        }
        clock++;
    }

}
