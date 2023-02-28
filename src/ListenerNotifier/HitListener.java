package ListenerNotifier;

import Sprites.Ball;
import Sprites.Block;

/**
 * Is the interface for all listener objects in game that need to
 * do a certain action when an event happens.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit is the object that is being hit
     * @param hitter The hitter parameter is the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
