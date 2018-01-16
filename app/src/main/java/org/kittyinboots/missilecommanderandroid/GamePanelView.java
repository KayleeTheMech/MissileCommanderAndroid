package org.kittyinboots.missilecommanderandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiMissile;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiObject;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiPosition;

import java.util.ArrayList;
import java.util.List;

public class GamePanelView extends SurfaceView implements SurfaceHolder.Callback {

    private static int windowHeight = -1;
    private static int windowWidth = -1;

    List<GuiObject> gameObjects;

    public GamePanelView(Context context) {
        super(context);
        initialize(context, null, 0);
    }

    public GamePanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs, 0);
    }

    public GamePanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        gameObjects = new ArrayList<>();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas = surfaceHolder.lockCanvas();
        draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
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

        for (GuiObject obj : gameObjects) {
            obj.onDraw(canvas);
        }
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

}
