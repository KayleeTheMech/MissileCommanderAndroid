package org.kittyinboots.missilecommanderandroid.core;


import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class SceneDirector implements Observer {
    private Core core;
    private Controller controller;


    public SceneDirector() {
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public void newGame() {
        core = new Core();
        controller = new Controller(core);
        core.addObserver(this);
    }

    public void addObserver(Observer object) {
        core.addObserver(object);
    }

    public void pause() {
        if (controller != null) {
            controller.pause();
        }
    }

    public void resume() {
        if (controller != null) {
            controller.resume();
        }
    }

    public void mouseClick(Position boardPosition) {
        controller.fireMissile(boardPosition);
    }

    public int getScore() {
        return core.getBase().getScore();
    }

    public List<GameObject> getGameObjects() {
        return core.getGameObjects();
    }

    public boolean isGameOngoing() {
        return core.getBase().isAlive();
    }
}
