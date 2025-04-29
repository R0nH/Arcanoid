//216727644 Ron Hefetz
package game;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import java.util.ArrayList;
import java.util.List;
/**Collection of all the collidables. */
public class GameEnvironment {
    private final List<Collidable> collidables;
    private final int borderWidth;
    private final int borderHeight;
    /**Constructor- reseting everything.
     * @param borderWidth width of drawing surface
     * @param borderHeight height of drawing surface*/
    public GameEnvironment(int borderWidth, int borderHeight) {
        collidables = new ArrayList<>();
        this.borderWidth = borderWidth;
        this.borderHeight = borderHeight;
    }
    /**@return border width */
    public int getBorderWidth() {
        return borderWidth;
    }
    /**@return border height */
    public int getBorderHeight() {
        return borderHeight;
    }
    // add the given collidable to the environment.
    /**add the given collidable to the environment.
     * @param c a collidable*/
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**remove the given collidable to the environment.
     * @param c a collidable*/
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }
    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    /**
     * @param trajectory object trajectory
     * @return info of the collision, if there is no collision then null*/
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (Collidable c : collidables) {
            Rectangle rect = c.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(rect);
            if (collisionPoint != null) {
                double distance = trajectory.start().distance(collisionPoint);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPoint = collisionPoint;
                    closestCollidable = c;
                }
            }
        }
        if (closestCollidable == null) {
            return null;
        }
        return new CollisionInfo(closestCollidable, closestPoint);
    }
}
