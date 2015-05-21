package com.tealcube.java.games.common.events;

public abstract class Event {

    private String name;

    public abstract HandlerList getHandlers();

    public String getName() {
        if (name == null) {
            name = getClass().getSimpleName();
        }
        return name;
    }

}
