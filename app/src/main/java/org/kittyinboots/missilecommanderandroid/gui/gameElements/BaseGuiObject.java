package org.kittyinboots.missilecommanderandroid.gui.gameElements;


import org.kittyinboots.missilecommanderandroid.gui.gameElements.shapes.BaseShape;

import java.util.List;

public class BaseGuiObject extends GuiObject {

    static final long serialVersionUID = 2001;
    private static final BaseShape baseShape = new BaseShape();

    public BaseGuiObject(GuiPosition position) {
        super(position);
    }

    /*
        BaseGuiObject(Base base) {
            super(base);

            initialize();
            fillColor = Color.white;
            borderColor = Color.white;
        }
    */
    @Override
    protected List<GuiPosition> getShape() {
        return baseShape.getMyShape();
    }
}
