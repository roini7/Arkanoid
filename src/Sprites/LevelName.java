package Sprites;

import Game.GameLevel;
import Levels.LevelInformation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level name.
 */
public class LevelName implements Sprite {
    private String levelName;

    /**
     * Instantiates a new Level name.
     *
     * @param levelInformation the level information
     */
    public LevelName(LevelInformation levelInformation) {
        this.levelName = levelInformation.levelName();
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d is the surface to draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(500, 25, "Level Name: " + levelName, 25);
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
