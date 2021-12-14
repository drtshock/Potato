package org.drtshock.api.events;

import org.drtshock.api.condiments.Condiment;

import java.util.ArrayList;
import java.util.List;

public class PotatoItemRemoveCondimentEvent implements Event, Cancellable {
    private final List<Event> handles = new ArrayList<>();
    private boolean canceled = false;

    public String condimentName;

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

    public String getCondimentName() {
        return condimentName;
    }

    public void setCondimentName(String condimentName) {
        this.condimentName = condimentName;
    }
}
