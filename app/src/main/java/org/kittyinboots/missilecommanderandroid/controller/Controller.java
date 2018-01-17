package org.kittyinboots.missilecommanderandroid.controller;

import org.kittyinboots.missilecommanderandroid.core.Core;
import org.kittyinboots.missilecommanderandroid.core.IDeactivate;
import org.kittyinboots.missilecommanderandroid.core.SceneDirector;

import java.util.Observable;
import java.util.Timer;

public class Controller extends Observable implements IDeactivate {

    public static final long DELAY = 5;
    public static final long PERIOD = 25;
    private boolean pause;
    private Core core;
    private SceneDirector director;
    private TimerRoutine timerRoutine;
    private Timer timer;

    public Controller(Core core, SceneDirector director) {
        this.core = core;
        this.director = director;
        this.timerRoutine = new TimerRoutine(this);
        timer = new Timer();
        timer.schedule(timerRoutine, DELAY, PERIOD);
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
        if (((timer == null) || (timerRoutine == null) || (core == null) || (director == null))) {
            throw new RuntimeException("Should not access controller after deactivation");
        }
    }

    public void deactivate() {
        internalCheck();
        this.deleteObservers();
        timer.cancel();
        timerRoutine.cancel();
        timerRoutine = null;
        timer = null;
        core = null;
        director = null;
    }
}
