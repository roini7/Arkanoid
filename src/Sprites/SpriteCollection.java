package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A class defining Sprites.Sprite collection of a game and the methods executed by it.
 */
public class SpriteCollection {
    private List<Sprite> spriteCollection;

    /**
     * Constructor that creates and array list of Sprites.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<Sprite>();
    }

    /**
     * Getter for Sprite Collection.
     * @return sprite collection
     */
    public List<Sprite> getSpriteCollection() {
        return spriteCollection;
    }

    /**
     * Add a sprite to the collection.
     *
     * @param s sprite to add
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /**
     * Remove a sprite from the sprite collection.
     *
     * @param s is the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteCollection = new ArrayList<>(getSpriteCollection());
        for (Sprite sprite : spriteCollection) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d draw surface to draw sprites on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : getSpriteCollection()) {
            sprite.drawOn(d);
        }
    }
}