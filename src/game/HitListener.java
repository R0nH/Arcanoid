//216727644 Ron Hefetz
package game;
/**listens when notifies about a hit. */
public interface HitListener {
    /** This method is called whenever the beingHit object is hit.
     * @param beingHit object being hit
     * @param hitter the ball hitting the object*/
    void hitEvent(Block beingHit, Ball hitter);
}
