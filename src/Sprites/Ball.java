package Sprites;

import Game.GameEnvironment;
import Game.GameLevel;
import Geometry.Line;
import Geometry.Point;
import biuoop.DrawSurface;
import Physics.Velocity;
import Collidables.CollisionInfo;

import java.awt.Color;

/**
 * Class describing ball and its attributes.
 */
public class Ball implements Sprite {
    private static final int DEFAULT_DX = 1, DEFAULT_DY = 1, MIN_SPEED = 2, MAX_SPEED = 250;
    private final int r;
    private Point center;
    private int x, y;
    private Color color = Color.WHITE;
    private Velocity v;
    private GameEnvironment gameEnvironment;
    private Paddle paddle = null;

    /**
     * Constructor for ball with center, radius and color values.
     *
     * @param center center point of ball
     * @param r      radius of ball
     * @param color  color of ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.x = (int) center.getX();
        this.y = (int) center.getY();
    }

    /**
     * Constructor for ball with x, y, radius, and color values.
     *
     * @param x     coordinate for ball starting point
     * @param y     coordinate for ball starting point
     * @param r     radius of ball
     * @param color color of ball
     */
    public Ball(int x, int y, int r, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.center = new Point(x, y);
    }

    /**
     * Constructor for ball with a specific size and a given center.
     *
     * @param size       size of ball
     * @param startPoint is the center of ball
     */
    public Ball(double size, Point startPoint) {
        this.r = (int) size;
        this.x = (int) startPoint.getX();
        this.y = (int) startPoint.getY();
        this.center = new Point(x, y);
    }

    /**
     * Set the ball's game environment to check for collidable objects.
     *
     * @param gameEnvironment is ball's game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * get x of center of ball.
     *
     * @return x coordinate of ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get y of center of ball.
     *
     * @return y coordinate of ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get center of ball.
     *
     * @return center point of ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * get size of ball (set to radius).
     *
     * @return size of ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * get color of ball.
     *
     * @return color of ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * draw ball on given draw surface.
     *
     * @param surface is a draw surface to draw onto
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * set velocity given differential of dx and dy.
     *
     * @param dx is the differential of x
     * @param dy is the differential of y
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * get speed of ball by its size.
     *
     * @return speed of ball
     */
    public int getSpeedBySize() {
        if (this.getSize() >= 50) {
            return MIN_SPEED;
        } else {
            return MAX_SPEED / this.getSize();
        }
    }

    /**
     * get velocity of ball.
     *
     * @return velocity of ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * set velocity of ball.
     *
     * @param v is the velocity to set to
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * move point of ball in next frame given ball velocity.
     */
    public void moveOneStep() {
        /*if ball has no velocity set it to default values*/
        if (this.getVelocity() == null) {
            this.setVelocity(DEFAULT_DX, DEFAULT_DY);
        }
        /*calculate the trajectory of ball with given velocity*/
        Point startPoint = this.center;
        Point endPoint = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(startPoint, endPoint);
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (this.isContained(paddle)) {
            this.center = this.paddle.closestPoint(this);
        } else if (collisionInfo == null) { // no collision occurred
            this.center = endPoint;
        } else {
            this.center = new Point(collisionInfo.collisionPoint().getX() - this.getVelocity().getDx(),
                    collisionInfo.collisionPoint().getY() - this.getVelocity().getDy());
            this.setVelocity(collisionInfo.collisionObject().
                    hit(this, collisionInfo.collisionPoint(), this.getVelocity()));
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        /*update x and y coordinates of ball*/
        this.x = (int) this.center.getX();
        this.y = (int) this.center.getY();
    }

    /**
     * call moveOneStep method to move the ball.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add ball to game using this method.
     *
     * @param gameLevel is the game to add ball to
     */
    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove ball from game.
     *
     * @param g is the game to remove ball from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Check if a ball is in the borders of the paddle.
     *
     * @param paddle is the paddle to check with
     * @return true if ball is contained in paddle, otherwise false
     */
    public boolean isContained(Paddle paddle) {
        return this.getX() > paddle.getCollisionRectangle().getbLine1().start().getX()
                && this.getX() < paddle.getCollisionRectangle().getbLine1().end().getX()
                && this.getY() < paddle.getCollisionRectangle().getbLine2().start().getY()
                && this.getY() > paddle.getCollisionRectangle().getbLine1().start().getY();

    }

    /**
     * Setter for associated paddle with ball.
     *
     * @param paddle paddle to associate with ball
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }
}