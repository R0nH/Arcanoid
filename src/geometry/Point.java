//216727644 Ron Hefetz
package geometry;
/**Point class. */
public class Point {
    private double x;
    private double y;
    // constructor
    /**@param x x value
     * @param y y value*/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // distance -- return the distance of this point to the other point
    /**@param other a point
     *@return the distance between the 2 this and other              */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    // equals -- return true is the points are equal, false otherwise
    /**@param other a point
     * @return true if other equals this otherwise false */
    public boolean equals(Point other) {
        double epsilon = 1e-4;
        return Math.abs(x - other.x) < epsilon && Math.abs(y - other.y) < epsilon;
    }
    // Return the x and y values of this point
    /**@return x */
    public double getX() {
        return this.x;
    }
    /**@return y */
    public double getY() {
        return this.y;
    }
    /**
     * @param x double */
    public void setX(double x) {
        this.x = x;
    }
    /**
     @param y double*/
    public void setY(double y) {
        this.y = y;
    }
    @Override
    public String toString() {
        return this.x + ", " + this.y;
    }
}
