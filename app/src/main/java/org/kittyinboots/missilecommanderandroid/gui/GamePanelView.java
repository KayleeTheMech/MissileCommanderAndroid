package org.kittyinboots.missilecommanderandroid.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.kittyinboots.missilecommanderandroid.controller.GameThread;
import org.kittyinboots.missilecommanderandroid.core.SceneDirector;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;
import org.kittyinboots.missilecommanderandroid.events.GameEvent;
import org.kittyinboots.missilecommanderandroid.events.GameEventType;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiObject;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class GamePanelView extends SurfaceView implements SurfaceHolder.Callback {

    private static int windowHeight = -1;
    private static int windowWidth = -1;
    private List<DelayedInfoString> console = new ArrayList<>();

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
        eventBus.register(this);
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
            // paintGameOver(canvas);
        }
        // console?
        displayConsole(canvas);
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    /*
        private void paintGameOver(Canvas canvas) {
            String gameOver = "GAME OVER";
            String exitToMenu = "Press ESC to return to menu";

            paintStringCentered(canvas, gameOver, Color.RED);
            paintStringCentered(canvas, exitToMenu, Color.RED);

        }

        private void paintStringCentered(Canvas canvas, String string, Color color) {
            canvas.drawText(string, x, y, );
        }
    */
    private void displayConsole(Canvas canvas) {
        updateConsole();
        drawConsole(canvas);
    }

    private void drawConsole(Canvas canvas) {
        Paint textPaint = getTextPaint(Color.GREEN, 16);
        int lineHeight = (int) ((textPaint.getFontMetrics().descent - textPaint.getFontMetrics().ascent) * 1.15);
        for (int i = 0; i < console.size(); i++) {
            String string = console.get(i).getString();
            canvas.drawText(string, 10, 32 + (lineHeight + 16) * i, textPaint);
        }
    }

    private void updateConsole() {
        List<DelayedInfoString> remove = new ArrayList<>();
        for (DelayedInfoString line : console) {
            if (!line.isActive()) {
                remove.add(line);
            }
        }
        console.removeAll(remove);
    }

    private TextPaint getTextPaint(int color, int fontSize) {
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextSize(fontSize * getResources().getDisplayMetrics().density);
        paint.setColor(color);
        return paint;
    }

    @Subscribe()
    public void eventHandler(GameEvent event) {
        if (event.getEventType() == GameEventType.ATTACK_WAVE_IS_OVER && event.getMetaData() != null) {
            if (event.getMetaData().getContentDescription().equals("level")) {
                int level = (int) event.getMetaData().getData();
                console.add(new DelayedInfoString("Wave " + level + "  cleared.", 2500));
            }

        }

        if (event.getEventType() == GameEventType.NEW_ATTACK_WAVE_INCOMING) {
            console.add(new DelayedInfoString("New wave of fighters incoming!", 1000));
        }

        if (event.getEventType() == GameEventType.NEW_GAME_HAS_BEGUN) {
            console.add(new DelayedInfoString("Prepare: Attack imminent", 1000));
        }

        if (event.getEventType() == GameEventType.ROCKET_FIRED) {
            console.add(new DelayedInfoString("Rocket Cost: -10 points", 100));
        }

        if (event.getEventType() == GameEventType.ENEMY_SHIP_KILLED) {
            console.add(new DelayedInfoString("Enemy killed: 250 points", 1000));
        }

        if (event.getEventType() == GameEventType.SURFACE_HIT_BY_ENEMY) {
            console.add(new DelayedInfoString("Surface Damage: -250 Points", 1000));
        }

        if (event.getEventType() == GameEventType.ENEMY_HAS_YOUR_LOCATION) {
            console.add(new DelayedInfoString("Enemy aiming at you!!", 500));
        }

        if (event.getEventType() == GameEventType.NEW_ENEMY_INBOUND) {
            console.add(new DelayedInfoString("New enemy in atmosphere", 1000));
        }
    }
}
