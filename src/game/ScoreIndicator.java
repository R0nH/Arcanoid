//216727644 Ron Hefetz
package game;
import geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;
/**Shows the score. */
public class ScoreIndicator extends Block {
    private final Counter score;
    /**
     * @param upperLeft super
     * @param width super
     * @param height super
     * @param color super
     * @param score the game's score*/
    public ScoreIndicator(Point upperLeft, int width, int height, Color color, Counter score) {
        super(upperLeft, width, height, color);
        this.score = score;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        return new Velocity(hitter.getVelocity().getDx(), hitter.getVelocity().getDy());
    }
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        int x = (int) (getUpperLeft().getX() + getWidth() / 2);
        int y = (int) (getUpperLeft().getY() + getHeight() - 7);
        String str = "Score: " + score.getValue();
        d.setColor(Color.BLACK);
        d.drawText(x, y, str, 15);
    }
}
