package org.drtshock;

import org.drtshock.exceptions.BurntException;
import org.drtshock.exceptions.NotDeliciousException;

public interface Tuber {
    boolean isDelicious();

    Tuber propagate();

    void prepare() throws NotDeliciousException, BurntException;
}
