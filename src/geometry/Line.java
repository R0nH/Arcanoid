//216727644 Ron Hefetz
package geometry;
import java.util.List;
/**Line class. */
public class Line {
    private final Point start;
    private final Point end;
    // constructors
    /**@param start start point
     * @param end end point*/
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**@param x1 x value of start point
     *@param y1 y value of start point
     * @param x2 x value of end point
     * @param y2 y value of end point*/
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    // Return the length of the line
    /**@return length of this line */
    public double length() {
        return this.start.distance(this.end);
    }
    // Returns the middle point of the line
    /**@return middle point of line */
    public Point middle() {
        double midX = (this.start().getX() + this.end.getX()) / 2;
        double midY = (this.start().getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }
    // Returns the start point of the line
    /**@return start point */
    public Point start() {
        return this.start;
    }
    // Returns the end point of the line
    /**@return end point */
    public Point end() {
        return this.end;
    }
    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    /**@param other a line
     *@return intersection point, if there are none or infinite return null*/
    // Inside your Line class
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();

        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * other.start.getX() + b2 * other.start.getY();

        double delta = a1 * b2 - a2 * b1;
        if (delta == 0) {
            return null; // Lines are parallel or coincident
        }

        double x = (b2 * c1 - b1 * c2) / delta;
        double y = (a1 * c2 - a2 * c1) / delta;

        // Check if the intersection point lies within the line segments
        if (x >= Math.min(this.start.getX(), this.end.getX()) && x <= Math.max(this.start.getX(), this.end.getX())
                && y >= Math.min(this.start.getY(), this.end.getY())
                && y <= Math.max(this.start.getY(), this.end.getY())
                && x >= Math.min(other.start.getX(), other.end.getX())
                && x <= Math.max(other.start.getX(), other.end.getX())
                && y >= Math.min(other.start.getY(), other.end.getY())
                && y <= Math.max(other.start.getY(), other.end.getY())) {
            return new Point(x, y);
        }
        return null;
    }
    // Returns true if the lines intersect, false otherwise
    /**@param other a line
     *@return true if this and other intersect, false otherwise*/
    public boolean isIntersecting(Line other) {
        double x1 = this.start().getX();
        double y1 = this.start().getY();
        double x2 = this.end().getX();
        double y2 = this.end().getY();
        double x3 = other.start().getX();
        double y3 = other.start().getY();
        double x4 = other.end().getX();
        double y4 = other.end().getY();
        if (this.intersectionWith(other) == null) {
            if ((x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1) && (x2 - x1) * (y4 - y1) == (y2 - y1) * (x4 - x1)) {
                if (Math.max(x1, x2) >= Math.min(x3, x4) && Math.max(x3, x4) >= Math.min(x1, x2)) {
                    return Math.max(y1, y2) >= Math.min(x3, x4) && Math.max(y3, y4) >= Math.min(y1, y2);
                }
            }
        }
        return false;
    }
    // Returns true if this 2 lines intersect with this line, false otherwise
    /**@param other1 line1
     * @param other2 line2
     * @return true if line 1 and line 2 intersect with this line*/
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }
    /**
     * @param other a line
     * @return true if other intersects with this in one point only otherwise false*/
    public boolean isOneIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }
    /**
     * @param other1 Line
     * @param other2 Line
     * @return true if intersecting int one dot with each line*/
    public boolean isOneIntersecting(Line other1, Line other2) {
        return this.intersectionWith(other1) != null && this.intersectionWith(other2) != null;
    }
    // equals -- return true is the lines are equal, false otherwise
    /**@param other a line
     * @return true if lines are equal up to a threshold */
    public boolean equals(Line other) {
        return this.start.equals(other.start()) && this.end.equals(other.end());
    }
    /**
     * @param rect a rectangle
     * @return closest intersection point, if there are none return null*/
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        if (intersections.size() == 1) {
            return intersections.get(0);
        }
        //there have to be only 2 intersection points then
        Point p1 = intersections.get(0);
        Point p2 = intersections.get(1);
        Point start = this.start();
        return p1.distance(start) < p2.distance(start) ? p1 : p2;
    }
}
