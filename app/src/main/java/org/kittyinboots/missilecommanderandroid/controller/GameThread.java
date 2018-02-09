package org.kittyinboots.missilecommanderandroid.controller;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import org.kittyinboots.missilecommanderandroid.core.SceneDirector;
import org.kittyinboots.missilecommanderandroid.gui.GamePanelView;

/**
 * Created by KayleeTheMech on 18.01.2018.
 */

public class GameThread extends Thread {

    private static final long frameTimeNano = 1000000000 / 30;

    private SceneDirector director;
    private SurfaceHolder surfaceHolder;
    private GamePanelView view;

    private boolean running;

    public GameThread(SceneDirector director, GamePanelView view, SurfaceHolder surfaceHolder) {
        this.director = director;
        this.view=view;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        long startTime;
        while (running) {
            startTime = System.nanoTime();
            // frame start
            director.getController().newFrame();
            Canvas canvas = null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();

                // Synchronized
                synchronized (canvas) {
                    this.view.draw(canvas);
                }
            } catch (Exception e) {
                // Do nothing.
            } finally {
                if (canvas != null) {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            // frame finished
            try {
                // rest the remaining time of the frame
                long frameDuration = System.nanoTime() - startTime;
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
