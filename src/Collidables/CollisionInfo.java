package Collidables;

import Geometry.Point;

/**
 * A class that defines collision info that consists of a collision point and a collision object.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collidableObject;

    /**
     * Constructor for collision point creating collision info out of a point and an object.
     *
     * @param collisionPoint   the collision point
     * @param collidableObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collidableObject) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = collidableObject;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return collidable object
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}
