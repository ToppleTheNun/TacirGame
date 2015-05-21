package com.tealcube.java.games.common.events;

public class RegisteredListener {

    private final Listener listener;
    private final EventPriority priority;
    private final EventExecutor executor;
    private final boolean ignoreCancelled;

    public RegisteredListener(Listener listener, EventPriority priority, EventExecutor executor,
                              boolean ignoreCancelled) {
        this.listener = listener;
        this.priority = priority;
        this.executor = executor;
        this.ignoreCancelled = ignoreCancelled;
    }

    public void callEvent(Event event) throws EventException {
        if (event instanceof Cancellable) {
            if (((Cancellable) event).isCancelled() && isIgnoreCancelled()) {
                return;
            }
        }
        executor.execute(listener, event);
    }

    public Listener getListener() {
        return listener;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public EventExecutor getExecutor() {
        return executor;
    }

    public boolean isIgnoreCancelled() {
        return ignoreCancelled;
    }
}
