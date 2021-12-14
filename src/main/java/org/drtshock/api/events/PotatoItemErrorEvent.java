package org.drtshock.api.events;

import java.util.ArrayList;
import java.util.List;

public class PotatoItemErrorEvent implements Event {
    private final List<Event> handles = new ArrayList<>();

    private String errorMessage;

    @Override
    public boolean execute(Event event) {
        for (Event e : handles)
            e.execute(event);

        return true;
    }

    public void addHandle(Event handle) {
        handles.add(handle);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
