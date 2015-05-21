package com.tealcube.java.games.common.events;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean value);

}
