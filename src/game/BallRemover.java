//216727644 Ron Hefetz
package game;
/**removes the ball when the player loses. */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;
    /**
     * @param game the game
     * @param remainingBalls counts the balls remaining in the game*/
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
