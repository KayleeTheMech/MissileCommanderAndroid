package org.kittyinboots.missilecommanderandroid.controller;

import org.kittyinboots.missilecommanderandroid.core.Core;
import org.kittyinboots.missilecommanderandroid.core.SceneDirector;

/**
 * Created by KayleeTheMech on 18.01.2018.
 */

public class GameThread extends Thread {

    private static final long frameTimeNano = 1000000000 / 30;

    private Controller controller;
    private Core core;
    private SceneDirector director;


    private boolean running;
    // surfaceHolder
    // gameSurface

    GameThread(Controller controller, Core core, SceneDirector director) {
        this.controller = controller;
        this.core = core;
        this.director = director;
    }

    @Override
    public void run() {
        long startTime;
        while (running) {
            startTime = System.nanoTime();
            // frame start
            controller.newFrame();
            // frame finished
            try {
                // rest the remaining time of the frame
                long frameDuration = System.nanoTime()-startTime;
                if (frameDuration < frameTimeNano) {
                    this.sleep((frameTimeNano - frameDuration) / 1000000);
                } else {
                    // dont sleep resources too low
                }
            } catch (InterruptedException e) {
                // woke up early
                // ¯\_(ツ)_/¯
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
