package Animations;

import biuoop.DrawSurface;

/**
 * Interface for animation handling.
 */
public interface Animation {
    /**
     * @param d
     */
    void doOneFrame(DrawSurface d);

    /**
     * condition for stopping the animation loop.
     *
     * @return boolean condition for stopping
     */
    boolean shouldStop();
}
