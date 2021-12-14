package org.drtshock.api.events;

public interface Cancellable {
    void setCanceled(boolean canceled);

    boolean isCanceled();
}
