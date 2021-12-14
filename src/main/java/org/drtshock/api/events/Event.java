package org.drtshock.api.events;

public interface Event {
    boolean execute(Event event);

    void addHandle(Event handle);
}
