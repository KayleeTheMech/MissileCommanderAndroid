package org.kittyinboots.missilecommanderandroid.events;

public class GameEventMetaData<T> {

    private String contentDescription;

    private T data;

    public GameEventMetaData(String contentDescription, T data) {
        this.data = data;
        this.contentDescription = contentDescription;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public T getData() {
        return data;
    }
}
