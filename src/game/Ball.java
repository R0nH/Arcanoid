//216727644 Ron Hefetz
package game;
import geometry.Point;
import geometry.Line;
import biuoop.DrawSurface;

/**class Ball. */
public class Ball implements Sprite {
    private Point center;
    private final int size;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    /**constructor.
     * @param center center of ball
     * @param r radius(size)
     * @param color color of ball*/
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }
    // accessors
    /**
     * @return x value of center of ball */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * @return y value of center */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * @return radius of ball */
    public int getSize() {
        return this.size;
    }
    /**
     * @return color of ball */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**@param color color of ball */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }
    /**draw the ball on the given DrawSurface.
     * @param surface drawing surface*/
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), size);
    }
    /**set velocity.
     * @param v velocity */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**set velocity.
     * @param dx x velocity
     * @param dy y velocity*/
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /** sets GE.
     * @param gameEnvironment a GE */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }
    /**get velocity.
     * @return ball's velocity */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**moves center one step.
     * @param horBorder1 left border
     * @param horBorder2 right border
     * @param verBorder1 upper border
     * @param verBorder2 lower border*/
    public void moveOneStep(int horBorder1, int horBorder2, int verBorder1, int verBorder2) {
        double epsilon = 1e-4;
        if (this.getX() >= horBorder2 - this.size - epsilon) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        if (this.getX() <= horBorder1 + this.size + epsilon) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        if (this.getY() >= verBorder2 - this.size - epsilon) {
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
        if (this.getY() <= verBorder1 + this.size + epsilon) {
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
        this.center = this.velocity.applyToPoint(this.center);
    }
    /**moves the ball one step. */
    public void moveOneStep() {
        double epsilon = 1e-4;
        double length = center.distance(velocity.applyToPoint(center));
        double ratio = size / length - epsilon;
        Line trajectory = new Line(center, velocity.applyToPoint(center));
        CollisionInfo collision = gameEnvironment.getClosestCollision(trajectory);
        if (collision != null) {
            Collidable collidable = collision.collisionObject();
            Point collisionPoint = collision.collisionPoint();
            double newX = collisionPoint.getX() - ratio * velocity.getDx();
            double newY = collisionPoint.getY() - ratio * velocity.getDy();
            center = new Point(newX, newY);
            velocity = collidable.hit(this, collision.collisionPoint(), velocity);
        }
        moveOneStep(0, 800, 25, 600);
    }
    /**moves one step after time passed. */
    public void timePassed() {
        this.moveOneStep();
    }
    /**adds ball to game.
     * @param g the game */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**removes this ball from the game.
     * @param g the game */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
