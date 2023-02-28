package ListenerNotifier;

import Sprites.Ball;
import Sprites.Block;

/**
 * Is a listener class for 'ScoreTracking' that updates current score
 * whenever a hitEvent occurs.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Is a constructor that holds reference to score in game.
     *
     * @param scoreCounter is the current score in game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}
