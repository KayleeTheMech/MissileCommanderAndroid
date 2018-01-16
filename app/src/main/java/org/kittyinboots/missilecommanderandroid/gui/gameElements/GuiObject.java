package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import org.kittyinboots.missilecommanderandroid.core.gameObjects.FlightObject;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;

import java.util.List;

public abstract class GuiObject {
    private GameObject gameObject;
    private GuiPosition direction;
    private GuiPosition centerOfMass;


    private static double stretchX = ((double) WindowWidth) / ((double) GAME_BOARD_WIDTH);
    private static double stretchY = ((double) WindowHeight) / ((double) GAME_BOARD_HEIGHT);

    int fillColor = Color.TRANSPARENT;
    int borderColor = Color.TRANSPARENT;

    public GuiObject(GameObject gameObject) {



        this.gameObject = gameObject;


        if (gameObject instanceof FlightObject) {
            this.direction = new GuiPosition(((FlightObject) gameObject).getTargetVector());
        }
        this.centerOfMass = new GuiPosition(gameObject.getPosition());
    }

    protected abstract List<GuiPosition> getShape();

    private Paint getBorderPaint() {
        Paint paint = new Paint();
        paint.setColor(borderColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }

    private Paint getFillPaint() {
        Paint paint = new Paint();
        paint.setColor(fillColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        return paint;
    }


    private Path getPath() {
        GuiPosition position = new GuiPosition(100, 100);
        Path path = new Path();
        if (getShape().size() > 0) {
            GuiPosition p = getShape().get(0);
            path.moveTo(p.getX() + position.getX(), p.getY() + position.getY());
            for (int i = 1; i < getShape().size(); i++) {
                p = getShape().get(i);
                path.lineTo(p.getX() + position.getX(), p.getY() + position.getY());
            }
            p = getShape().get(0);
            path.lineTo(p.getX() + position.getX(), p.getY() + position.getY());
            return path;
        } else {
            return path;
        }
    }

    public void onDraw(Canvas canvas) {
        canvas.drawPath(getPath(), getFillPaint());
        canvas.drawPath(getPath(), getBorderPaint());
    }
}
