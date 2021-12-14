package org.drtshock.api.events;

import java.util.ArrayList;
import java.util.List;

public class PotatoItemCreateEvent implements Event, Cancellable {
    private final List<Event> handles = new ArrayList<>();
    private boolean canceled = false;

    @Override
    public boolean execute(Event event) {
        for (Event e : handles)
            e.execute(event);

        return isCanceled();
    }

    public void addHandle(Event handle) {
        handles.add(handle);
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
}
