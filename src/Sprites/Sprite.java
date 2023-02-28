package Sprites;

import Game.GameLevel;
import biuoop.DrawSurface;

/**
 * The sprite interface defines a game object called 'Sprites.Sprite' and its attributes.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     * @param d is the surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add sprite to game.
     * @param gameLevel is the game to add to
     */
    void addToGame(GameLevel gameLevel);
}