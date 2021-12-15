package org.drtshock.api.extensions;

import org.drtshock.api.DelectableItem;
import org.drtshock.api.Extension;

import java.util.ArrayList;
import java.util.List;

public class Plate implements Extension {
    private final List<DelectableItem> items = new ArrayList<>();

    public void visuliseItems() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Plate{" +
                "items=" + items +
                '}';
    }

    @Override
    public void appendItem(DelectableItem item) {
        items.add(item);
    }

    @Override
    public void removeItem(int index) {
        items.remove(index);
    }

    @Override
    public int indexOf(DelectableItem item) {
        return items.indexOf(item);
    }
}
