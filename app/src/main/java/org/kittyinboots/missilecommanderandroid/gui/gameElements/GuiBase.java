package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import android.graphics.Color;

import org.kittyinboots.missilecommanderandroid.core.gameObjects.Base;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes.BaseShape;

import java.util.List;

public class GuiBase extends GuiObject {

    static final long serialVersionUID = 2001;
    private static final BaseShape baseShape = new BaseShape();


    GuiBase(Base base) {
        super(base);

        initialize();
        fillColor = Color.WHITE;
        borderColor = Color.WHITE;
    }

    @Override
    protected List<GuiPosition> getShape() {
        return baseShape.getMyShape();
    }
}
