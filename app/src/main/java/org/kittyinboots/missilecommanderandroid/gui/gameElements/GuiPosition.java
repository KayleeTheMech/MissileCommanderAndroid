package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import org.kittyinboots.missilecommanderandroid.gui.GamePanelView;
import org.kittyinboots.missilecommanderandroid.core.Position;

import static org.kittyinboots.missilecommanderandroid.core.Core.GAME_BOARD_HEIGHT;
import static org.kittyinboots.missilecommanderandroid.core.Core.GAME_BOARD_WIDTH;

public class GuiPosition {
    private float x;
    private float y;


    private static double stretchX = ((double) GamePanelView.getWindowWidth()) / ((double) GAME_BOARD_WIDTH);
    private static double stretchY = ((double) GamePanelView.getWindowHeight()) / ((double) GAME_BOARD_HEIGHT);

    public GuiPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public GuiPosition(Position boardPosition) {
        // Koordinatentransform intern to gui
        this.x = (int) ((boardPosition.getX() * stretchX + GamePanelView.getWindowWidth()) / 2);
        this.y = (int) (GamePanelView.getWindowHeight() - stretchY * boardPosition.getY());
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDrehWinkel(GuiPosition targetPosition) {
        int vergleichX = -10; // und (y=0)
        float vectorX = targetPosition.getX() - this.x;
        float vectorY = targetPosition.getY() - this.y;
        float skalarProdukt = vergleichX * vectorX;
        float laengeVector = (float) Math.sqrt(vectorX * vectorX + vectorY * vectorY);
        float winkel = (float) (180 / Math.PI * Math.acos(skalarProdukt / (10 * laengeVector)));
        if (vectorY < 0) {
            return (winkel);
        } else {
            return -(winkel);
        }
    }
}
