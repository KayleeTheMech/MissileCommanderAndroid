package org.kittyinboots.missilecommanderandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.common.eventbus.EventBus;

import org.kittyinboots.missilecommanderandroid.controller.Controller;
import org.kittyinboots.missilecommanderandroid.controller.GameThread;
import org.kittyinboots.missilecommanderandroid.core.SceneDirector;
import org.kittyinboots.missilecommanderandroid.gui.GamePanelView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        EventBus eventBus = new EventBus();
        SceneDirector director = new SceneDirector(eventBus);
        director.newGame();
        GamePanelView view = new GamePanelView(this, eventBus, director);
        this.setContentView(view);
    }
}
