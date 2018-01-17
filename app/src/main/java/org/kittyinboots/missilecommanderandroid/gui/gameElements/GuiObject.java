package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import org.kittyinboots.missilecommanderandroid.gui.GamePanelView;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.FlightObject;
import org.kittyinboots.missilecommanderandroid.core.gameObjects.GameObject;

import java.util.List;

import static org.kittyinboots.missilecommanderandroid.core.Core.GAME_BOARD_HEIGHT;
import static org.kittyinboots.missilecommanderandroid.core.Core.GAME_BOARD_WIDTH;

public abstract class GuiObject {

    protected GameObject gameObject;
    private GuiPosition direction;
    private GuiPosition centerOfMass;


    private static double stretchX = ((double) GamePanelView.getWindowWidth()) / ((double) GAME_BOARD_WIDTH);
    private static double stretchY = ((double) GamePanelView.getWindowHeight()) / ((double) GAME_BOARD_HEIGHT);

    int fillColor = Color.TRANSPARENT;
    int borderColor = Color.TRANSPARENT;

    public GuiObject(GameObject gameObject) {

        this.gameObject = gameObject;
        if (gameObject instanceof FlightObject) {
            this.direction = new GuiPosition(((FlightObject) gameObject).getTargetVector());
        }
        this.centerOfMass = new GuiPosition(gameObject.getPosition());
    }

    /**
     * This method needs to be overwritten by any GameObject. An ordered List of GUIPositions is expected in order to construct the
     * edges of the polygon.
     *
     * @return a List of GuiPositions that define the objects shape
     */
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

    //FIXME old methods


    /**
     * Turning the path
     */
    void rotateShapeArrays(double extraAngle) {
        /*
        if (direction == null || centerOfMass == null) {
            throw new RuntimeException("Class not correctly extended. To rotate there needs to be a center of mass and a direction");
        }

        double alpha = centerOfMass.getDrehWinkel(direction) + extraAngle;
        for (int i = 0; i < (x.length + y.length) / 2; i++) {
            int xOld = x[i];
            int yOld = y[i];
            x[i] = (int) (xOld * Math.cos(alpha) - yOld * Math.sin(alpha));
            y[i] = -(int) (xOld * Math.sin(alpha) + yOld * Math.cos(alpha));
        }
        */
    }

    /**
     * Moving the polygon array to the position on screen
     */
    private void moveShapeArrays() {
        /*
        if (centerOfMass == null) {
            throw new RuntimeException("Class not correctly extended. To move the array there needs to be a center of mass.");

        }
        for (int i = 0; i < (x.length + y.length) / 2; i++) {
            x[i] = centerOfMass.getX() + x[i];
            if (x[i] < 0) {
                x[i] = 0;
            }
            y[i] = centerOfMass.getY() + y[i];
            if (y[i] < 0) {
                y[i] = 0;
            }
        }
        */
    }

    void initialize() {
    }
}
