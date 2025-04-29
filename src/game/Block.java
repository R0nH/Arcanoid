//216727644 Ron Hefetz
package game;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**A rectangle that can be hit. */
public class Block extends Rectangle implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners;
    /**
     * @param upperLeft upper left corner
     * @param width width of block
     * @param height height of block
     * @param color color of block*/
    public Block(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
        hitListeners = new ArrayList<>();
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.getUpperLeft(), this.getWidth(), this.getHeight(), this.getColor());
    }
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double epsilon = 1e-4;
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        //(x0, y0) represent upper left corner of block
        double x0 = this.getUpperLeft().getX();
        double y0 = this.getUpperLeft().getY();
        double x1 = x0 + this.getWidth();
        double y1 = y0 + this.getHeight();
        // Check for collision with sides
        if (Math.abs(x - x0) < epsilon || Math.abs(x - x1) < epsilon) {
            dx = -dx;
        }
        // Check for collision with top or bottom edge
        if (Math.abs(y - y0) < epsilon || Math.abs(y - y1) < epsilon) {
            dy = -dy;
        }
        // Check for corner collision
        if ((Math.abs(x - x0) < epsilon && Math.abs(y - y0) < epsilon)
                || (Math.abs(x - x1) < epsilon && Math.abs(y - y0) < epsilon)
                || (Math.abs(x - x0) < epsilon && Math.abs(y - y1) < epsilon)
                || (Math.abs(x - x1) < epsilon && Math.abs(y - y1) < epsilon)) {
            dx = -dx;
            dy = -dy;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        hitter.setColor(getColor());
        return new Velocity(dx, dy);
    }
    /**
     * @param ball ball hitting
     * @return true if colors match*/
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.getColor());
    }
    /**Removes this from the game.
     * @param game the game*/
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    @Override
    public void timePassed() {
    }
    void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
