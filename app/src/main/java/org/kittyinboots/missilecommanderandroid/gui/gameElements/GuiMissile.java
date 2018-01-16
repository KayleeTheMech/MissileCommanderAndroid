package org.kittyinboots.missilecommanderandroid.gui.gameElements;


import android.graphics.Color;

import org.kittyinboots.missilecommanderandroid.core.gameObjects.Missile;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes.MissileShape;

import java.util.List;

public class GuiMissile extends GuiObject {

    static final long serialVersionUID = 2001;
    private static final MissileShape shape = new MissileShape();

    GuiMissile(Missile missile) {
        super(missile);
        rotateShapeArrays(0);
        initialize();
        fillColor = Color.GRAY;
        borderColor = Color.WHITE;
    }

    @Override
    protected List<GuiPosition> getShape() {
        return shape.getMyShape();
    }

}
