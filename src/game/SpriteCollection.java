//216727644 Ron Hefetz
package game;
import biuoop.DrawSurface;
import java.util.ArrayList;
/**All sprites collection. */
public class SpriteCollection {
    private final ArrayList<Sprite> sprites;
    /**Constructor. */
    public SpriteCollection() {
        sprites = new ArrayList<>();
    }
    /** Constructs a new sprite collection with a shallow copy as sprites.
     * @param sc a Sprite collection */
    public SpriteCollection(SpriteCollection sc) {
        this.sprites = (ArrayList<Sprite>) sc.sprites.clone();
    }
    /**Add a sprite.
     * @param s a Sprite */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**Remove a sprite.
     * @param s a Sprite */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**call timePassed() on all sprites. */
    public void notifyAllTimePassed() {
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }
    /**call drawOn(d) on all sprites.
     * @param d A draw surface*/
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
