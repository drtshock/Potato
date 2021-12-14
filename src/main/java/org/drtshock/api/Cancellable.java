package org.drtshock.api;

public interface Cancellable {
    void setCanceled(boolean canceled);

    boolean isCanceled();
}
