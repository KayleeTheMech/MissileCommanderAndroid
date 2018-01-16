package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import org.kittyinboots.missilecommanderandroid.core.Position;

public class GuiPosition {
    private float x;
    private float y;

    public GuiPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public GuiPosition(Position boardPosition) {
        // Koordinatentransform intern to gui
        this.x = (int) (boardPosition.getX() * stretchX + WindowWidth / 2);
        this.y = (int) (WindowHeight - stretchX * boardPosition.getY());
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
