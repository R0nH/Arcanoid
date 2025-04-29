//216727644 Ron Hefetz
package geometry;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**Rectangle class. */
public class Rectangle {
    private Point upperLeft;
    private final double width;
    private final double height;
    private Color color;
    // Create a new rectangle with location and width/height.
    /**
     * @param height height of rectangle
     * @param width width of rectangle
     * @param upperLeft upper left corner of rectangle
     * @param color color of rectangle*/
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    /**@return color of rectangle */
    public Color getColor() {
        return color;
    }
    /**sets upper left corner.
     * @param upperLeft a point */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }
    /**sets color.
     * @param color a color */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * @param line a line
     * @return Return a (possibly empty) List of intersection points with the specified line.*/
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> lst = new ArrayList<>();
        Point p1, p2, p3, p4;
        p1 = new Point(upperLeft.getX(), upperLeft.getY());
        p2 = new Point(upperLeft.getX() + width, upperLeft.getY());
        p3 = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        p4 = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Line line1, line2, line3, line4;
        line1 = new Line(p1, p2);
        line2 = new Line(p2, p3);
        line3 = new Line(p3, p4);
        line4 = new Line(p4, p1);
        if (line1.isOneIntersecting(line)) {
            lst.add(line1.intersectionWith(line));
        }
        if (line2.isOneIntersecting(line)) {
            lst.add(line2.intersectionWith(line));
        }
        if (line3.isOneIntersecting(line)) {
            lst.add(line3.intersectionWith(line));
        }
        if (line4.isOneIntersecting(line)) {
            lst.add(line4.intersectionWith(line));
        }
        return lst;
    }
    /**
     * @return Return the width of the rectangle*/
    public double getWidth() {
        return width;
    }
    /**
     * @return Return the height of the rectangle */
    public double getHeight() {
        return height;
    }
    /**
     * @return Returns the upper-left point of the rectangle. */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /** draws the rectangle on d.
     * @param d a draw surface */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }
}
