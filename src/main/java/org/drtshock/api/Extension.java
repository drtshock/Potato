package org.drtshock.api;

public interface Extension {
    void appendItem(DelectableItem item);
    void removeItem(int index);
    int indexOf(DelectableItem item);
}
