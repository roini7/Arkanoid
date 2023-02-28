package Game;

import Collidables.CollisionInfo;
import Geometry.Line;
import Geometry.Point;
import Collidables.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that defines the game's environment with collidable objects and implements methods related to thus
 * environment and it's attributes.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Constructor to create an array list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * get list of collidables.
     *
     * @return list of collidables in game environment
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Remove a collidable from the game environment.
     * @param c is the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Method to add collidable object to collidables in this environment.
     *
     * @param c is the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * find closest' intersection point of a trajectory with collidable objects in game environment.
     *
     * @param trajectory is a line that ball travels in
     * @return closest' intersection point with a collidable
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int isColliding = 0;
        List<Point> collisionPoints = new ArrayList<>();
        List<Collidable> collidableObjects = new ArrayList<>();
        /*check for collision with collidables and add them to collisionPoints list and collidable objects list*/
        for (Collidable collidable : getCollidables()) {
            Point collision = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collision != null) {
                isColliding = 1;
                collisionPoints.add(collision);
                collidableObjects.add(collidable);
            }
        }
        /*if no object is colliding*/
        if (isColliding != 1) {
            return null;
        } else {
            Point closestPoint = collisionPoints.get(0);
            Collidable closestCollidable = collidableObjects.get(0);
            /*find closest' collision point with a collidable*/
            for (int i = 1; i < collisionPoints.size(); i++) {
                if (trajectory.start().distance(collisionPoints.get(i)) < trajectory.start().distance(closestPoint)) {
                    closestPoint = collisionPoints.get(i);
                    closestCollidable = collidableObjects.get(i);
                }
            }
            return new CollisionInfo(closestPoint, closestCollidable);
        }

    }
}
