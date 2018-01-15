package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.List;

public abstract class GuiObject {
    private GuiPosition position;

    int fillColor = Color.TRANSPARENT;
    int borderColor = Color.TRANSPARENT;

    public GuiObject(GuiPosition position) {
        this.position = position;
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
