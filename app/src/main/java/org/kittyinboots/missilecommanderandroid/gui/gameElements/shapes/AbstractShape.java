package org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes;


import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiPosition;

import java.util.List;

public abstract class AbstractShape {
    protected List<GuiPosition> myShape;


    public List<GuiPosition> getMyShape() {
        return myShape;
    }
}
