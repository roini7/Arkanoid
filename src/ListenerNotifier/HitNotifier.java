package ListenerNotifier;

/**
 * Is the interface for all notifiers in game that need to send
 * a message when an event occurs.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl is the listener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl is the listener to remove
     */
    void removeHitListener(HitListener hl);
}
