package org.drtshock.api;

public interface Event {
    boolean execute(Event event);

    void addHandle(Event handle);
}
