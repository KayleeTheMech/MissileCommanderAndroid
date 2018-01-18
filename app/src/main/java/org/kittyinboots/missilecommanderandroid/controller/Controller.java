package org.kittyinboots.missilecommanderandroid.controller;

import org.kittyinboots.missilecommanderandroid.core.Core;
import org.kittyinboots.missilecommanderandroid.core.IDeactivate;
import org.kittyinboots.missilecommanderandroid.core.SceneDirector;

import java.util.Observable;

public class Controller extends Observable implements IDeactivate {

    private boolean pause;
    private Core core;
    private SceneDirector director;

    public Controller(Core core, SceneDirector director) {
        this.core = core;
        this.director = director;
    }

    public boolean isPaused() {
        internalCheck();
        return pause;
    }

    public void pause() {
        internalCheck();
        pause = true;
    }

    public void resume() {
        internalCheck();
        pause = false;
    }

    void newFrame() {
        if (!pause) {
            core.newFrame();
            director.newFrame();
        }
    }

    private void internalCheck() {
        if (((core == null) || (director == null))) {
            throw new RuntimeException("Should not access controller after deactivation");
        }
    }

    public void deactivate() {
        internalCheck();
        this.deleteObservers();
        core = null;
        director = null;
    }
}
