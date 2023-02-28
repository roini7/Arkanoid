package Collidables;

import Geometry.Point;
import Geometry.Rectangle;
import Physics.Velocity;
import Sprites.Ball;
import biuoop.DrawSurface;

/**
 * Interface for all collidable classes that defines basic methods for such objects.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return Geometry.Rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * calculate the new velocity from a collision point with collidable and object's current velocity.
     *
     * @param collisionPoint  is the collision point with a block
     * @param currentVelocity is the velocity of the object colliding with said block
     * @param hitter is the ball that is doing the hitting
     * @return new velocity after calculation
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Method for drawing a collidable object.
     *
     * @param d is the surface to draw to
     */
    void drawOn(DrawSurface d);
}
