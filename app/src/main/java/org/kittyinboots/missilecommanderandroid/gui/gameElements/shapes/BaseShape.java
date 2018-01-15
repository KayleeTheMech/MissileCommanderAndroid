package org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes;


import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiPosition;

import java.util.ArrayList;

public class BaseShape extends AbstractShape {
    public BaseShape() {
        myShape = new ArrayList<>();
        final int width = 60;
        final int height = 40;
        myShape.add(new GuiPosition(-width, -height));
        myShape.add(new GuiPosition(-width, +0));
        myShape.add(new GuiPosition(+width, +0));
        myShape.add(new GuiPosition(+width, -height));
        myShape.add(new GuiPosition(+width / 2, -height / 2));
        myShape.add(new GuiPosition(-width / 2, -height / 2));
    }
}
