package ListenerNotifier;

import Game.GameLevel;
import Sprites.Ball;
import Sprites.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor for blockRemover listener class.
     *
     * @param gameLevel          is the game that blockRemover corresponds with
     * @param removedBlocks are the remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Is the event in which blocks that are being hit are removed from the game.
     *
     * @param beingHit is the block that is being hit
     * @param hitter   is the ball that is doing the hitting
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease(1);
    }
}