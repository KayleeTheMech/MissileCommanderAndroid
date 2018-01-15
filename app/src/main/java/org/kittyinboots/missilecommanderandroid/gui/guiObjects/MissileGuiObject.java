package org.kittyinboots.missilecommanderandroid.gui.guiObjects;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class MissileGuiObject extends GuiObject {

    public MissileGuiObject(GuiPosition position) {
        super(position);
    }

    @Override
    ArrayList<GuiPosition> getShape() {
        final int length = 50;
        final int width = 20;

        ArrayList<GuiPosition> myShape = new ArrayList<>();
        myShape.add(new GuiPosition(-(length * 1 / 2), +0));
        myShape.add(new GuiPosition(-(length * 3 / 8), -(width * 1 / 2)));
        myShape.add(new GuiPosition(-(length * 1 / 4), -(width * 1 / 4)));
        myShape.add(new GuiPosition(+(length * 3 / 8), -(width * 1 / 4)));
        myShape.add(new GuiPosition(+(length * 1 / 2), -(width * 1 / 2)));
        myShape.add(new GuiPosition(+(length * 1 / 2), (width * 1 / 2)));
        myShape.add(new GuiPosition(+(length * 3 / 8), (width * 1 / 4)));
        myShape.add(new GuiPosition(-(length * 1 / 4), (width * 1 / 4)));
        myShape.add(new GuiPosition(-(length / 2 * 3 / 4), (width * 1 / 2)));
        return myShape;
    }

    @Override
    Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
}
