package Physics;

import Geometry.Point;

/**
 * Physics.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Physics.Velocity constructor taking dx,dy differentials.
     *
     * @param dx differential of x to move on x lane
     * @param dy differential of y to move on y lane
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * a static method to get velocity from angle and speed values.
     *
     * @param angle angle of ball
     * @param speed speed of ball
     * @return Physics.Velocity by angle and speed converted to dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleR = Math.toRadians(angle);
        double dx = speed * Math.sin(angleR);
        double dy = speed * Math.cos(angleR);
        return new Velocity(dx, -1 * dy);
    }

    /**
     * return the speed of the ball.
     *
     * @return speed of ball
     */
    public double getSpeed() {
        return Math.sqrt((getDx() * getDx()) + (getDy() * getDy()));
    }

    /**
     * get differential of x of ball.
     *
     * @return differential of x
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Setter for Dx of velocity.
     *
     * @param dx new dx to set to
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * get differential of y of ball.
     *
     * @return differential of y
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Setter for Dy of velocity.
     *
     * @param dy new dy to set to
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p new Geometry.Point with applied Physics.Velocity to it
     * @return new point with applied values of velocity
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x, y);
    }
}
