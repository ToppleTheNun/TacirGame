package com.tealcube.java.games.common.events;

public interface EventExecutor {

    void execute(Listener listener, Event event) throws EventException;

}
