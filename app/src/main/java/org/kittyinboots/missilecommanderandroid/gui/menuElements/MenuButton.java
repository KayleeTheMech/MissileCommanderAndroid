package org.kittyinboots.missilecommanderandroid.gui.menuElements;

import org.kittyinboots.missilecommanderandroid.gui.GamePanelView;
import org.kittyinboots.missilecommanderandroid.gui.gameElements.GuiPosition;

public class MenuButton {
    public static final int width = GamePanelView.getWindowWidth() / 2;
    public static final int height = GamePanelView.getWindowWidth() / 15;

    private final float leftBorder;
    private final float rightBorder;
    private final float topBorder;
    private final float bottomBorder;

    //TODO find a way to display text in android
    // private Font menuFont = new Font("sans", Font.BOLD, 20);

    private GuiPosition leftUpperCorner;
    private String buttonText;

    public MenuButton(String buttonText, GuiPosition leftUpperCorner) {
        this.leftUpperCorner = leftUpperCorner;
        this.buttonText = buttonText;

        leftBorder = leftUpperCorner.getX();
        rightBorder = leftUpperCorner.getX() + width - 1;
        topBorder = leftUpperCorner.getY();
        bottomBorder = leftUpperCorner.getY() + height - 1;
    }
/* TODO write an onDraw function for the canvas
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(leftBorder, topBorder, width, height);
        g.setColor(Color.ORANGE);
        g.fillRect(leftBorder + 1, topBorder + 1, width - 1, height - 1);
        g.setColor(Color.BLACK);
        g.setFont(menuFont);
        int stringWidth = g.getFontMetrics(menuFont).stringWidth(buttonText);
        int stringHeight = g.getFontMetrics(menuFont).getHeight();
        g.drawString(buttonText, leftBorder + (width - stringWidth) / 2, topBorder + height / 2 + stringHeight / 4);
    }
    */

    public boolean isLocatedWithinShape(GuiPosition p) {
        return (p.getX() > leftBorder) && (p.getX() < rightBorder) &&
                (p.getY() > topBorder) && (p.getY() < bottomBorder);
    }
}
