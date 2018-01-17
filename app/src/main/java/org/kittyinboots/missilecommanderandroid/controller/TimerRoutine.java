package org.kittyinboots.missilecommanderandroid.controller;

import java.util.TimerTask;

public class TimerRoutine extends TimerTask {
    private Controller controller;

    TimerRoutine(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        controller.newFrame();
    }

}
