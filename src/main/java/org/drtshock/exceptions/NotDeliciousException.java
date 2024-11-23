package org.drtshock.exceptions;

import org.drtshock.types.NotDeliciousReason;

/**
 * An exception to describe that the potato isn't delicious!
 */
public class NotDeliciousException extends Exception {

    /**
     * The reason for non-deliciousness.
     */
    private NotDeliciousReason notDeliciousReason;

    public NotDeliciousException(NotDeliciousReason notDeliciousReason) {
        this.notDeliciousReason = notDeliciousReason;
    }

    public NotDeliciousReason getReason() {
        return notDeliciousReason;
    }
}
