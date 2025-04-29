//216727644 Ron Hefetz
package game;
/**notifies to listener when hit. */
public interface HitNotifier {
    /** Add hl as a listener to hit events.
     * @param hl a listener */
    void addHitListener(HitListener hl);
    /** Remove hl from the list of listeners to hit events.
     * @param hl a listener */
    void removeHitListener(HitListener hl);
}
