//216727644 Ron Hefetz
package game;
import geometry.Point;
import geometry.Rectangle;
import biuoop.KeyboardSensor;
import biuoop.GUI;
import java.awt.Color;
/**Paddle class. */
public class Paddle extends Rectangle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    /**Constructor.
     * @param upperLeft super, upper left corner of paddle
     * @param width super, width of paddle
     * @param height super, height of paddle
     * @param gui the gui where we set the keyboard*/
    public Paddle(Point upperLeft, double width, double height, GUI gui) {
        super(upperLeft, width, height, Color.ORANGE);
        keyboard = gui.getKeyboardSensor();
    }
    /** Move left if the user has pressed the left arrow. */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            Point p = this.getUpperLeft();
            double newX = p.getX() - 4;
            if (newX < 0) {
                // Wrap around to the right side
                newX = 800 - this.getWidth();
            }
            setUpperLeft(new Point(newX, p.getY()));
        }
    }

    /** Move right if the user has pressed the right arrow. */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            Point p = this.getUpperLeft();
            double newX = p.getX() + 4;
            if (newX > 800 - this.getWidth()) {
                // Wrap around to the left side
                newX = 0;
            }
            setUpperLeft(new Point(newX, p.getY()));
        }
    }
    // Sprite
    /**Notify the sprite that time has passed. */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }
    // Collidable
    /**@return collision rectangle. */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.getUpperLeft(), this.getWidth(), this.getHeight(), this.getColor());
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double epsilon = 1e-4;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Get the x and y coordinates of the collision point relative to the paddle
        double collisionX = collisionPoint.getX() - this.getUpperLeft().getX();
        double collisionY = collisionPoint.getY() - this.getUpperLeft().getY();

        // Determine which region was hit if the collision is from the top or bottom
        double regionWidth = this.getWidth() / 5;
        int region = (int) (collisionX / regionWidth) + 1;
        double angle;
        double speed = Math.sqrt(dx * dx + dy * dy);

        // Check if the collision is from the top or bottom
        if (collisionY <= epsilon) { // Collision from the top
            switch (region) {
                case 1:
                    angle = Math.toRadians(150); // 30 degrees to the left
                    break;
                case 2:
                    angle = Math.toRadians(120); // 60 degrees to the left
                    break;
                case 3:
                    return new Velocity(dx, -dy); // Straight bounce
                case 4:
                    angle = Math.toRadians(60); // 60 degrees to the right
                    break;
                case 5:
                    angle = Math.toRadians(30); // 30 degrees to the right
                    break;
                default:
                    // This case should never happen
                    return new Velocity(dx, dy);
            }

            // Convert the angle and speed back to dx and dy
            dx = speed * Math.cos(angle);
            dy = -speed * Math.sin(angle); // Negate dy because positive dy is down
            return new Velocity(dx, dy);
        } else if (collisionY >= this.getHeight() - epsilon) { // Collision from the bottom
            switch (region) {
                case 1:
                    angle = Math.toRadians(210); // 30 degrees to the left
                    break;
                case 2:
                    angle = Math.toRadians(240); // 60 degrees to the left
                    break;
                case 3:
                    return new Velocity(dx, dy); // Straight bounce
                case 4:
                    angle = Math.toRadians(300); // 60 degrees to the right
                    break;
                case 5:
                    angle = Math.toRadians(330); // 30 degrees to the right
                    break;
                default:
                    // This case should never happen
                    return new Velocity(dx, dy);
            }

            // Convert the angle and speed back to dx and dy
            dx = speed * Math.cos(angle);
            dy = speed * Math.sin(angle); // Keep dy positive to go downwards
            return new Velocity(dx, dy);
        } else {
            // Check if the collision is from the sides
            if (collisionX <= epsilon || collisionX >= this.getWidth() - epsilon) {
                // Collision from the left or right
                return new Velocity(-dx, dy);
            } else {
                // This should never happen, return the same velocity
                return new Velocity(dx, dy);
            }
        }
    }
    /**Add this paddle to the game.
     * @param g the game */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}
