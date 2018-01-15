package org.kittyinboots.missilecommanderandroid.gui.guiObjects;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.List;

public abstract class GuiObject {
    private GuiPosition position;

    public GuiObject(GuiPosition position) {
        this.position = position;
    }

    abstract List<GuiPosition> getShape();

    abstract Paint getPaint();

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
        canvas.drawPath(getPath(), getPaint());
    }
}
