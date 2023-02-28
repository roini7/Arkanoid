package Sprites;

import Game.GameLevel;
import Collidables.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import ListenerNotifier.HitListener;
import ListenerNotifier.HitNotifier;
import biuoop.DrawSurface;
import Physics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Sprites.Block class defines a block and it's attributes. block is a sort of Geometry.Rectangle object in a game.
 * the methods in this class implement collidable and sprite methods for block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double ERROR_MARGIN = Math.pow(10, -10);
    private final Rectangle block;
    private List<HitListener> hitListeners;

    /**
     * Constructor for block creating a block from a given rectangle.
     *
     * @param block is a Geometry.Rectangle
     */
    public Block(Rectangle block) {
        this.block = block;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * draw block onto screen.
     *
     * @param d drawing surface to draw block onto
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getCollisionRectangle().getColor());
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        /*Outline color is set to black*/
        d.setColor(Color.BLACK);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
    }

    /**
     * None implemented method from Sprites.Sprite(for now :) ).
     */
    @Override
    public void timePassed() {

    }

    /**
     * Get the rectangle defining block.
     *
     * @return Geometry.Rectangle of block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return block;
    }

    /**
     * Hit implementation for block given a collision point and a velocity.
     * calculate the new velocity from a collision point with block and object's current velocity.
     *
     * @param collisionPoint  is the collision point with a block
     * @param currentVelocity is the velocity of the object colliding with said block
     * @return new velocity after calculation
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // object hits corners of block
        if (collisionPoint == block.getbLine1().start() || collisionPoint == block.getbLine1().end()
                || collisionPoint == block.getbLine2().start() || collisionPoint == block.getbLine2().end()) {
            currentVelocity.setDx(-1 * currentVelocity.getDx());
            currentVelocity.setDy(-1 * currentVelocity.getDy());
            // object hits baselines of block
        } else if (collisionPoint.getY() == block.getbLine1().start().getY()
                || collisionPoint.getY() == block.getbLine2().start().getY()
                || Math.abs(collisionPoint.getY() - block.getbLine1().start().getY()) < ERROR_MARGIN
                || Math.abs(collisionPoint.getY() - block.getbLine2().start().getY()) < ERROR_MARGIN) {
            currentVelocity.setDy(-1 * currentVelocity.getDy());
            currentVelocity.setDx(currentVelocity.getDx());
            // object hits adjacent lines of block
        } else if (collisionPoint.getX() == block.getaLine1().start().getX()
                || collisionPoint.getX() == block.getaLine2().start().getX()
                || Math.abs(collisionPoint.getX() - block.getbLine1().start().getX()) < ERROR_MARGIN
                || Math.abs(collisionPoint.getX() - block.getbLine2().start().getX()) < ERROR_MARGIN) {
            currentVelocity.setDx(-1 * currentVelocity.getDx());
            currentVelocity.setDy(currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * Add block to game as collidable and sprite.
     *
     * @param gameLevel is the game to add Sprites.Block to
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Remove this block from game.
     *
     * @param gameLevel is the game to remove block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
