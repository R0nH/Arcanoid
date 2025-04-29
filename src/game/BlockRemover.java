//216727644 Ron Hefetz
package game;
/**Listener that removes blocks when is notified. */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;
    /**
     * @param game the game
     * @param remainingBlocks number of blocks remaining*/
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    /**
     * @param hitter the ball hitting the block
     * @param beingHit the block being hit*/
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }

}
