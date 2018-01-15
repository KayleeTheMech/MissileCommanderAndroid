package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import android.graphics.Color;

import org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes.UfoShape;

import java.util.List;

/**
 * This class manually defines the shape of an enemy UFO. It further rotates and moves this values to fit the object the shape
 * shall represent.
 */
public class UfoGuiObject extends GuiObject {

    static final long serialVersionUID = 2001;
    private static final UfoShape ufoShape = new UfoShape();

    public UfoGuiObject(GuiPosition position) {
        super(position);
    }


    @Override
    protected List<GuiPosition> getShape() {
        return ufoShape.getMyShape();
    }
}
