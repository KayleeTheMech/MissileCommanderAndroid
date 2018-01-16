package org.kittyinboots.missilecommanderandroid.gui.gameElements;


import org.kittyinboots.missilecommanderandroid.core.gameObjects.Base;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.Explosion;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.Missile;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.UFO;

public class GuiObjectFactory {

    public GuiObject getGUIObject(GameObject gameObject) {
        if (gameObject instanceof UFO) {
            return new GuiUfo((UFO) gameObject);
        } else if (gameObject instanceof Missile) {
            return new GuiMissile((Missile) gameObject);
        } else if (gameObject instanceof Explosion) {
            return new GuiExplosion((Explosion) gameObject);
        } else if (gameObject instanceof Base) {
            return new GuiBase((Base) gameObject);
        } else {
            if (gameObject == null) {
                throw new RuntimeException("null is not a GameObject");
            }
            if (gameObject.getClass() == null) {
                throw new RuntimeException("GameObject " + gameObject.toString() + " has no class. Weird.");
            }
            throw new RuntimeException("GameObject " + gameObject.getClass().toString() + "is of a not supported Class");
        }
    }
}

