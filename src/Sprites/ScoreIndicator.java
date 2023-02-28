package Sprites;

import Game.GameLevel;
import ListenerNotifier.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Is a class that defines a 'ScoreIndicator' sprite attributes in game,
 * and it's implemented methods.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Constructor for score indicator.
     *
     * @param score is the current score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d is the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(35, 25, "Score:" + score.getValue(), 25);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add sprite to game.
     *
     * @param gameLevel is the game to add to
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
