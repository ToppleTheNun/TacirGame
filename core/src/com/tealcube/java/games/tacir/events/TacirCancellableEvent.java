package com.tealcube.java.games.tacir.events;

import com.tealcube.games.java.common.events.Cancellable;

public class TacirCancellableEvent extends TacirEvent implements Cancellable {

    private boolean cancelled;

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
