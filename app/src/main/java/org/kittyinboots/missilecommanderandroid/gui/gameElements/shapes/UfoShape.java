package org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes;

import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiPosition;

import java.util.ArrayList;

public class UfoShape extends AbstractShape {

    public UfoShape() {
        final int breite = 32;
        final int hoehe = 32;

        myShape = new ArrayList<>();
        myShape.add(new GuiPosition(+(int) ((double) 0 * breite), +(int) ((double) 6 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 2 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 4 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 14 / 16 * breite), +(int) ((double) 4 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 14 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 15 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 16 / 16 * breite), +(int) ((double) 1 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 8 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 4 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GuiPosition(+(int) ((double) 2 / 16 * breite), -(int) ((double) 5 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 2 / 16 * breite), -(int) ((double) 5 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 4 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 8 / 16 * breite), +(int) ((double) 0 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 16 / 16 * breite), +(int) ((double) 1 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 15 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 14 / 16 * breite), +(int) ((double) 10 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 14 / 16 * breite), +(int) ((double) 4 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 4 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));
        myShape.add(new GuiPosition(-(int) ((double) 2 / 16 * breite), +(int) ((double) 2 / 16 * hoehe)));

    }
}
