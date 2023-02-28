package Sprites;

import Collidables.Collidable;
import Geometry.Point;
import Geometry.Rectangle;
import Physics.Velocity;
import Game.GameLevel;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Class paddle defines a paddle as a sprite and a collidable(implementing their methods)
 * with properties block and keyboard and different methods for using paddle in game.
 */
public class Paddle implements Sprite, Collidable {
    private static final double ERROR_MARGIN = Math.pow(10, -10);
    private static final int FRAME_WIDTH = 800, FRAME_HEIGHT = 600, BORDER_HEIGHT = 30, GAP = 0,
            SECTOR1_ANGLE = 300, SECTOR2_ANGLE = 330, SECTOR3_ANGLE = 30, SECTOR4_ANGLE = 60, PADDLE_HEIGHT = 20;
    private static final Color PADDLE_COLOR = Color.YELLOW;
    private static final double SECTOR1 = 0.2, SECTOR2 = 0.4, SECTOR3 = 0.6, SECTOR4 = 0.8;
    private Block paddle;
    private biuoop.KeyboardSensor keyboard;
    private int speed;

    /**
     * Constructor for paddle, paddle is defined by a block and a keyboard.
     *
     * @param gui         is the gui to bind the keyboard of paddle to
     * @param width       is the width of paddle
     * @param speed       is the speed of the paddle
     */
    public Paddle(GUI gui, int width, int speed) {
        this.paddle = new Block(new Rectangle(
                new Point((double) FRAME_WIDTH / 2 - (double) width / 2,
                        FRAME_HEIGHT - BORDER_HEIGHT - PADDLE_HEIGHT),
                width, PADDLE_HEIGHT, PADDLE_COLOR));
       this.keyboard = gui.getKeyboardSensor();
       this.speed = speed;
    }

    /**
     * Move paddle to the left.
     */
    public void moveLeft() {
        paddle.getCollisionRectangle().setUpperLeft(
                new Point(paddle.getCollisionRectangle().getUpperLeft().getX() - speed,
                        paddle.getCollisionRectangle().getUpperLeft().getY()));
    }

    /**
     * Move paddle to the right.
     */
    public void moveRight() {
        paddle.getCollisionRectangle().setUpperLeft(
                new Point(paddle.getCollisionRectangle().getUpperLeft().getX() + speed,
                        paddle.getCollisionRectangle().getUpperLeft().getY()));
    }

    /**
     * Draw paddle onto draw surface.
     *
     * @param d is the drawSurface to draw paddle onto
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getCollisionRectangle().getColor());
        d.fillRectangle((int) paddle.getCollisionRectangle().getUpperLeft().getX(),
                (int) paddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) paddle.getCollisionRectangle().getWidth(),
                (int) paddle.getCollisionRectangle().getHeight());
        /*outline of paddle is black*/
        d.setColor(Color.BLACK);
        d.drawRectangle((int) paddle.getCollisionRectangle().getUpperLeft().getX(),
                (int) paddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) paddle.getCollisionRectangle().getWidth(),
                (int) paddle.getCollisionRectangle().getHeight());
    }

    /**
     * Move the paddle according to user keyboard input.
     */
    public void timePassed() {
        /*if left key is pressed move left up to left border*/
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && this.getCollisionRectangle().getUpperLeft().getX() > BORDER_HEIGHT + GAP) {
            this.moveLeft();
        }
        /*if right key is pressed move right up to right border*/
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && this.getCollisionRectangle().getUpperLeft().getX()
                < FRAME_WIDTH - BORDER_HEIGHT - this.getCollisionRectangle().getWidth() - GAP) {
            this.moveRight();
        }
    }

    /**
     * get the shape of the collidable(Geometry.Rectangle).
     *
     * @return Geometry.Rectangle collided with
     */
    public Rectangle getCollisionRectangle() {
        return paddle.getCollisionRectangle();
    }

    /**
     * hit implementation for paddle.
     * given a certain collision point and velocity of an object, calculate the new velocity after collision
     * with object using paddle's game logic
     *
     * @param collisionPoint  is the collision point with and object
     * @param currentVelocity is the velocity of the object
     * @param hitter is the ball that's doing the hitting
     * @return the new velocity after collision occurred
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        double x1 = this.getCollisionRectangle().getbLine1().start().getX();
        double x5 = this.getCollisionRectangle().getbLine1().end().getX();
        double paddleLength = this.getCollisionRectangle().getWidth();
        /*collision is in sector 1*/
        if (collisionPoint.getY() == this.getCollisionRectangle().getUpperLeft().getY()
                && (collisionPoint.getX() >= x1 && collisionPoint.getX() <= x1 + (paddleLength * SECTOR1))) {
            newVelocity = Velocity.fromAngleAndSpeed(SECTOR1_ANGLE, hitter.getVelocity().getSpeed());
        } else if (collisionPoint.getX() > x1 + (paddleLength * SECTOR1) // collision is in sector 2
                && (collisionPoint.getX() <= x1 + (paddleLength * SECTOR2)
                || Math.abs(collisionPoint.getX() - (x1 + (paddleLength * SECTOR2))) < ERROR_MARGIN)) {
            newVelocity = Velocity.fromAngleAndSpeed(SECTOR2_ANGLE, hitter.getVelocity().getSpeed());
        } else if (collisionPoint.getX() > x1 + (paddleLength * SECTOR2) //collision is in sector 3
                && (collisionPoint.getX() <= x1 + (paddleLength * SECTOR3)
                || Math.abs(collisionPoint.getX() - (x1 + (paddleLength * SECTOR3))) < ERROR_MARGIN)) {
            newVelocity.setDy(-1 * newVelocity.getDy());
        } else if (collisionPoint.getX() > x1 + (paddleLength * SECTOR3)
                && (collisionPoint.getX() <= x1 + (paddleLength * SECTOR4)
                || Math.abs(collisionPoint.getX() - (x1 + (paddleLength * SECTOR4))) < ERROR_MARGIN)) {
            //collision is in sector 4
            newVelocity = Velocity.fromAngleAndSpeed(SECTOR3_ANGLE, hitter.getVelocity().getSpeed());
        } else if (collisionPoint.getY() == this.getCollisionRectangle().getUpperLeft().getY()
                && collisionPoint.getX() > x1 + (paddleLength * SECTOR4) && collisionPoint.getX() <= x5) {
            /*collision is in sector 5*/
            newVelocity = Velocity.fromAngleAndSpeed(SECTOR4_ANGLE, hitter.getVelocity().getSpeed());
        } else if (collisionPoint.getX() == paddle.getCollisionRectangle().getaLine1().start().getX()
                || collisionPoint.getX() == paddle.getCollisionRectangle().getaLine2().start().getX()) {
            /*object hits adjacent lines of block*/
            newVelocity.setDx(-1 * currentVelocity.getDx());
            newVelocity.setDy(currentVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * Add paddle to game.
     *
     * @param g is the game to add paddle to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Find closest' point to side's of paddle if ball is contained in paddle.
     *
     * @param ball to check closest' point for
     * @return closest' point to side's of paddle
     */
    public Point closestPoint(Ball ball) {
        Point p1 = new Point(this.getCollisionRectangle().getaLine1().start().getX(), ball.getY());
        Point p2 = new Point(this.getCollisionRectangle().getaLine2().start().getX(), ball.getY());
        if (p1.distance(ball.getCenter()) < p2.distance(ball.getCenter())) {
            return p1;
        } else {
            return p2;
        }
    }
}