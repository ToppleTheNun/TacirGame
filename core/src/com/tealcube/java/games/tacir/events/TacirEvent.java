package com.tealcube.java.games.tacir.events;

import com.tealcube.games.java.common.events.Event;
import com.tealcube.games.java.common.events.HandlerList;

public class TacirEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

}
