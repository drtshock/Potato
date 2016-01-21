package org.drtshock;

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

}
