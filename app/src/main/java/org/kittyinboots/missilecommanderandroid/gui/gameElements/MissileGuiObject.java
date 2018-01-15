package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import android.graphics.Color;

import org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes.MissileShape;

import java.util.ArrayList;
import java.util.List;

public class MissileGuiObject extends GuiObject {

    private static final MissileShape missileShape = new MissileShape();

    public MissileGuiObject(GuiPosition position) {
        super(position);
        fillColor = Color.GRAY;
        borderColor = Color.WHITE;
    }

    @Override
    protected List<GuiPosition> getShape() {
        return missileShape.getMyShape();
    }

}
