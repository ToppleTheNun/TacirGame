package com.tealcube.java.games.common.events;

public class EventException extends Exception {

    private final Throwable cause;

    public EventException(Throwable cause) {
        this.cause = cause;
    }

    public EventException(String message, Throwable cause) {
        super(message);
        this.cause = cause;
    }

    public EventException(String message) {
        super(message);
        this.cause = null;
    }

    public EventException() {
        this.cause = null;
    }

    @Override
    public Throwable getCause() {
        return this.cause;
    }
}
