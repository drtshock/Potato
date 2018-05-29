package org.drtshock;

public interface Tuber {

    boolean isDelicious() throws NotDeliciousException;

    Tuber propagate();

}
