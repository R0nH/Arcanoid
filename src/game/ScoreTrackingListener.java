//216727644 Ron Hefetz
package game;
/**listens to block and updates the score when notified. */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;
    /**
     * @param scoreCounter the score of the game */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
