//216727644 Ron Hefetz
package game;
import geometry.Point;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
/**The Game. */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;
    /**Constructor: create new empty sprite collection and game environment. */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment(800, 600);
        gui = new GUI("Game", 800, 600);
        remainingBlocks = new Counter();
        remainingBalls = new Counter();
        score = new Counter();
    }
    /**add a collidable to the game.
     * @param c a collidable */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**add a sprite to the game.
     * @param s a sprite */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**remove a collidable to the game.
     * @param c a collidable */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }
    /**remove a sprite to the game.
     * @param s a sprite */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
    /**Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.*/
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        Paddle paddle = new Paddle(new Point(300, 500), 75, 25, gui);
        paddle.addToGame(this);
        for (int i = 0; i < 3; i++) { //Creating the balls
            Ball ball = new Ball(new Point(100 + 100 * i, 400), 10, Color.BLACK);
            ball.setVelocity(3, -2);
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            remainingBalls.increase(1);
        }
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        for (int i = 0; i < 6; i++) { //Creating the blocks
            for (int j = i; j < 12; j++) {
                Block block = new Block(new Point(150 + 51 * j, 100 + 26 * i), 50, 25, colors[i]);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                remainingBlocks.increase(1);
            }
        }
        Block death = new Block(new Point(0, 530), 800, 70, Color.BLACK);
        death.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        death.addHitListener(ballRemover);
        ScoreIndicator si = new ScoreIndicator(new Point(0, 0), 800, 25, Color.GRAY, score);
        si.addToGame(this);
        si.addHitListener(scoreTrackingListener);
    }
    /**Run the game -- start the animation loop. */
    public void run() {
        Sleeper sleeper = new Sleeper();
        boolean flag = false;
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            SpriteCollection sprites = new SpriteCollection(this.sprites);
            sprites.drawAllOn(d);
            gui.show(d);
            sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (flag) {
                return;
            }
            if (remainingBalls.getValue() == 0 || remainingBlocks.getValue() == 0) {
                if (remainingBlocks.getValue() == 0) {
                    score.increase(100);
                }
                flag = true;
            }
        }
    }
}
