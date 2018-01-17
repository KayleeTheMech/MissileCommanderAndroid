package org.kittyinboots.missilecommanderandroid.gui;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

class DelayedInfoString {

    private String string;
    private int millis;

    private Stopwatch sw;

    DelayedInfoString(String text, int millis) {
        this.millis = millis;
        this.string = text;
        sw = Stopwatch.createStarted();
    }

    boolean isActive() {
        return sw.elapsed(TimeUnit.MILLISECONDS) < millis;
    }

    String getString() {
        return string;
    }
}
