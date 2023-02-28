package ListenerNotifier;

import Game.GameLevel;
import Sprites.Ball;
import Sprites.Block;

/**
 * BallRemover listener class that corresponds with game objects.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor for ball remover 'listener' class.
     *
     * @param gameLevel           is the game that the listener corresponds to
     * @param remainingBalls are the remaining balls in game
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (remainingBalls.getValue() == 0) {
            beingHit.removeHitListener(this);
        } else {
            remainingBalls.decrease(1);
            hitter.removeFromGame(gameLevel);
        }
    }
}
