//216727644 Ron Hefetz
package game;
import geometry.Point;
import geometry.Rectangle;
/**every collidable object, like the blocks or the paddle. */
public interface Collidable {
    /**
     * @return Return the "collision shape" of the object.*/
    Rectangle getCollisionRectangle();
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * @param hitter hitting ball
     * @param collisionPoint collision point
     * @param currentVelocity current velocity of the object colliding
     * @return the new velocity after the hit*/
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
