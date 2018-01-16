package org.kittyinboots.missilecommanderandroid.events;

public class GameEvent {
    private GameEventType eventType;

    private GameEventMetaData metaData;

    public GameEvent(GameEventType eventType) {
        this.eventType = eventType;
        this.metaData = null;
    }

    public GameEvent(GameEventType eventType, GameEventMetaData metaData) {
        this.eventType = eventType;
        this.metaData = metaData;
    }

    public GameEventType getEventType() {
        return eventType;
    }

    public boolean hasMetaData() {
        if (metaData == null) {
            return false;
        } else return true;
    }

    public GameEventMetaData getMetaData() {
        return metaData;
    }
}
