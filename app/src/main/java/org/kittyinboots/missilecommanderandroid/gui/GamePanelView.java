package org.kittyinboots.missilecommanderandroid.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.google.common.eventbus.EventBus;

import org.kittyinboots.missilecommanderandroid.controller.GameThread;
import org.kittyinboots.missilecommanderandroid.core.Position;
import org.kittyinboots.missilecommanderandroid.core.SceneDirector;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.Missile;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.UFO;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiMissile;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiObject;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiObjectFactory;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiPosition;

import java.util.ArrayList;
import java.util.List;

public class GamePanelView extends SurfaceView implements SurfaceHolder.Callback {

    private static int windowHeight = -1;
    private static int windowWidth = -1;
    private SurfaceHolder surfaceHolder;

    EventBus eventBus;
    SceneDirector director;
    private GuiObjectFactory factory;

    public GamePanelView(Context context, EventBus eventBus,
                         SceneDirector director) {
        super(context);
        initialize(context, null, 0, eventBus, director);
    }

    public GamePanelView(Context context, AttributeSet attrs, EventBus eventBus,
                         SceneDirector director) {
        super(context, attrs);
        initialize(context, attrs, 0, eventBus, director);
    }

    public GamePanelView(Context context, AttributeSet attrs, int defStyleAttr, EventBus eventBus,
                         SceneDirector director) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr, eventBus, director);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs, int defStyleAttr, EventBus eventBus,
                            SceneDirector director) {
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        this.director = director;
        this.eventBus = eventBus;
        factory = new GuiObjectFactory();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        GameThread gameThread = new GameThread(director, this, surfaceHolder);
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        windowWidth = this.getMeasuredWidth();
        windowHeight = this.getMeasuredHeight();
        List<GameObject> gameObjects = director.getGameObjects();
        for (GameObject obj : gameObjects) {
            GuiObject graphicalObject = factory.getGUIObject(obj);
            graphicalObject.onDraw(canvas);
        }
        if (!director.isGameOngoing()) {
            // draw game over if over
        }
        // console?
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

}
