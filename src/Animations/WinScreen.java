package Animations;

import ListenerNotifier.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Win screen.
 */
public class WinScreen extends AnimationDecorator implements Animation {
    private Counter score;

    /**
     * Instantiates a new Win screen.
     *
     * @param stoppableAnimation the stoppable animation
     * @param score              the score
     */
    public WinScreen(KeypressStoppableAnimation stoppableAnimation, Counter score) {
        super(stoppableAnimation);
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        super.doOneFrame(d);
        d.setColor(Color.WHITE);
        d.drawText(50, 300, "You Win! Your Score is " + this.score.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return super.shouldStop();
    }
}
