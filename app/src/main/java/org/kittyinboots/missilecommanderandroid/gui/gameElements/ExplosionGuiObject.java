package org.kittyinboots.missilecommanderandroid.gui.gameElements;


import java.util.List;

public class ExplosionGuiObject extends GuiObject {

    final static long serialVersionUID = 2001;

    public ExplosionGuiObject(GuiPosition position) {
        super(position);
    }

    /*
        ExplosionGuiObject(Explosion explosion) {
            super(explosion);
            initialize();
            fillColor = Color.yellow;
            borderColor = Color.red;
        }
    */
    @Override
    public List<GuiPosition> getShape() {
        /*
        int radius = ((Explosion) this.gameObject).getDetonationRadius();
        int numberOfPoints = (int) (radius * 2 * Math.PI) / 4;
        double winkelabschnitt = 2 * Math.PI / numberOfPoints;

        List<GuiPosition> points = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            double r = 1;
            if (i % 2 == 1) {
                r = r * 1.2;
            }
            int x = (int) (radius * r * Math.cos(i * winkelabschnitt));
            int y = (int) (radius * r * Math.sin(i * winkelabschnitt));
            points.add(new GuiPosition(x, y));
        }
        return points;
            */
        return null;
    }
}
