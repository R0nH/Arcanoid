//216727644 Ron Hefetz
package game;
import biuoop.DrawSurface;
/**game objects. */
public interface Sprite {
    /**draw the sprite to the screen.
     * @param d the drawing surface */
    void drawOn(DrawSurface d);
    /**notify the sprite that time has passed. */
    void timePassed();
}
