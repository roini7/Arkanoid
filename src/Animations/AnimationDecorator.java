package Animations;

import biuoop.DrawSurface;

/**
 * The type Animation decorator.
 */
public abstract class AnimationDecorator implements Animation {
    /**
     * The Stoppable animation.
     */
    private KeypressStoppableAnimation stoppableAnimation;

    /**
     * Instantiates a new Animation decorator.
     *
     * @param stoppableAnimation the stoppable animation
     */
    public AnimationDecorator(KeypressStoppableAnimation stoppableAnimation) {
        this.stoppableAnimation = stoppableAnimation;
    }

    /**
     * getter for stoppableAnimation.
     * @return stoppableAnimation
     */
    public KeypressStoppableAnimation getStoppableAnimation() {
        return stoppableAnimation;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        getStoppableAnimation().doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.getStoppableAnimation().shouldStop();
    }
}
