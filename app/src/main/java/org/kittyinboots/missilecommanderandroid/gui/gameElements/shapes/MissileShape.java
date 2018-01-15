package org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes;


import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiPosition;

import java.util.ArrayList;

public class MissileShape extends AbstractShape {

    public MissileShape() {
        final int length = 50;
        final int width = 20;

        myShape = new ArrayList<>();
        myShape.add(new GuiPosition(-(length * 1 / 2), +0));
        myShape.add(new GuiPosition(-(length * 3 / 8), -(width * 1 / 2)));
        myShape.add(new GuiPosition(-(length * 1 / 4), -(width * 1 / 4)));
        myShape.add(new GuiPosition(+(length * 3 / 8), -(width * 1 / 4)));
        myShape.add(new GuiPosition(+(length * 1 / 2), -(width * 1 / 2)));
        myShape.add(new GuiPosition(+(length * 1 / 2), (width * 1 / 2)));
        myShape.add(new GuiPosition(+(length * 3 / 8), (width * 1 / 4)));
        myShape.add(new GuiPosition(-(length * 1 / 4), (width * 1 / 4)));
        myShape.add(new GuiPosition(-(length / 2 * 3 / 4), (width * 1 / 2)));
    }
}
