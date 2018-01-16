package org.kittyinboots.missilecommanderandroid.gui.gameElements;

import org.kittyinboots.missilecommanderandroid.GamePanelView;
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
}
