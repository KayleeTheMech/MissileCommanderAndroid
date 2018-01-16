package org.kittyinboots.missilecommanderandroid.core;

import com.google.common.eventbus.EventBus;

import org.kittyinboots.missilecommanderandroid.core.gameObjects.Explosion;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObjectFactory;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.Missile;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.UFO;
import org.kittyinboots.missilecommanderandroid.events.GameEvent;
import org.kittyinboots.missilecommanderandroid.events.GameEventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Core extends Observable implements IDeactivate {
    public final static int GAME_BOARD_WIDTH = 500;
    public final static int GAME_BOARD_HEIGHT = 1000;


    // Actual variables
    private List<GameObject> gameObjects;
    private GameObjectFactory objectFactory;

    private EventBus eventBus;

    Core(EventBus eventBus) {
        gameObjects = new ArrayList<>();
        objectFactory = new GameObjectFactory();
        this.eventBus = eventBus;
    }

    public List<GameObject> getGameObjects() {
        internalCheck();
        List<GameObject> returnList = new ArrayList<>();
        returnList.addAll(gameObjects);
        return returnList;
    }

    public void newFrame() {
        internalCheck();
        missileIgnitionRoutine();
        destructionRoutine();
        this.setChanged();
        this.notifyObservers();
        deleteDecayedExplosions();
    }

    private <Type> List<Type> getObjectType(Class<Type> typeClass) {
        List<Type> retList = new ArrayList<>();
        for (GameObject object : getGameObjects()) {
            if (typeClass.isInstance(object)) {
                retList.add((Type) object);
            }
        }
        return retList;
    }

    private void explodeObject(GameObject object) {
        gameObjects.remove(object);
        object.kill();
        Explosion explosion = objectFactory.makeExplosion(object.getPosition(), object.getDetonationRadius());
        eventBus.post(new GameEvent(GameEventType.EXPLOSION));
        addGameObject(explosion);
    }


    private void missileIgnitionRoutine() {
        List<Missile> toExplode = new ArrayList<>();
        for (Missile missile : getObjectType(Missile.class)) {
            if (missile.reachedTarget()) {
                toExplode.add(missile);
            } else {
                for (UFO ufo : getObjectType(UFO.class)) {
                    if (missile.withinRange(ufo.getPosition())) {
                        toExplode.add(missile);
                    }
                }
            }
        }
        for (GameObject object : toExplode) {
            explodeObject(object);
        }
    }

    private void destructionRoutine() {
        List<GameObject> toExplode = new ArrayList<>();
        List<Explosion> explosions = getObjectType(Explosion.class);
        List<GameObject> notExplosions = getGameObjects();
        notExplosions.removeAll(explosions);
        for (GameObject gameObject : notExplosions) {
            for (Explosion explosion : explosions) {
                if (explosion.withinRange(gameObject.getPosition())) {
                    if (gameObject instanceof UFO) {
                        eventBus.post(new GameEvent(GameEventType.ENEMY_SHIP_KILLED));
                    }
                    toExplode.add(gameObject);
                }
            }

            if ((gameObject.getPosition().getY() < 0) && gameObject instanceof UFO) {
                eventBus.post(new GameEvent(GameEventType.SURFACE_HIT_BY_ENEMY));
                toExplode.add(gameObject);
            }
        }
        for (GameObject object : toExplode) {
            explodeObject(object);
        }
    }

    private void deleteDecayedExplosions() {
        List<Explosion> toBeRemoved = new ArrayList<>();
        List<Explosion> allExplosions = getObjectType(Explosion.class);
        for (Explosion explosion : allExplosions) {
            if (explosion.getDetonationRadius() <= 1) {
                toBeRemoved.add(explosion);
            }
        }
        gameObjects.removeAll(toBeRemoved);
    }

    void addGameObject(GameObject object) {
        internalCheck();
        this.addObserver(object);
        this.gameObjects.add(object);
    }

    public void deactivate() {
        internalCheck();
        this.deleteObservers();
        this.gameObjects.clear();
        this.gameObjects = null;
        this.objectFactory = null;
    }

    private void internalCheck() {
        if (((gameObjects == null) || (this.objectFactory == null))) {
            throw new RuntimeException("Should not access core after deactivation");
        }
    }


}
