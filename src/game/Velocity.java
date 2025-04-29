//216727644 Ron Hefetz
package game;
import geometry.Point;
/**Velocity class. */
public class Velocity {
    private final double dx;
    private final double dy;
    // constructor
    /**@param dx dx
     *@param dy  dy*/
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    /**
     * @param p a point
     * @return point plus velocity*/
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /** converts to dx dy terms.
     * @param angle angle of ball
     * @param speed speed of ball
     * @return velocity in dx dy terms*/
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }
    /**
     * @return dx */
    public double getDx() {
        return dx;
    }
    /**
     * @return dy */
    public double getDy() {
        return dy;
    }
}
