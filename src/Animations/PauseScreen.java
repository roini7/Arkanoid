package Animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen extends AnimationDecorator implements Animation {
    /**
     * Instantiates a new Pause screen.
     *
     * @param stoppableAnimation the stoppable animation
     */
    public PauseScreen(KeypressStoppableAnimation stoppableAnimation) {
        super(stoppableAnimation);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        getStoppableAnimation().doOneFrame(d);
        d.setColor(Color.WHITE);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return getStoppableAnimation().shouldStop();
    }
}