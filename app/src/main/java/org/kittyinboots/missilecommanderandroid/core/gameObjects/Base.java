package org.kittyinboots.missilecommanderandroid.core.gameObjects;

import com.google.common.eventbus.EventBus;

import org.kittyinboots.missilecommanderandroid.events.GameEvent;

import static org.kittyinboots.missilecommanderandroid.events.GameEventType.PLAYER_HAS_DIED;

public class Base extends GameObject {

    private EventBus eventBus;

    Base(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
    }

    @Override
    public void kill() {
        super.kill();
        eventBus.post(new GameEvent(PLAYER_HAS_DIED));
    }

}
