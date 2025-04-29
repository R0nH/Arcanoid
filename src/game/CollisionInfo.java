//216727644 Ron Hefetz
package game;
import geometry.Point;
/**info of a collision. */
public class CollisionInfo {
    private final Collidable collidable;
    private final Point collisionPoint;
    /**Constructor.
     * @param collidable the collidable collided into
     * @param collisionPoint the collision point*/
    public CollisionInfo(Collidable collidable, Point collisionPoint) {
        this.collidable = collidable;
        this.collisionPoint = collisionPoint;
    }
    // the point at which the collision occurs.
    /**
     * @return collision point */
    public Point collisionPoint() {
        return collisionPoint;
    }
    // the collidable object involved in the collision.
    /**
     * @return the collidable */
    public Collidable collisionObject() {
        return collidable;
    }
}
