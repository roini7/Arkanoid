package Geometry;

/**
 * Class for point defining its attributes.
 */
public class Point {
    private double x;
    private double y;
    private static final double ERROR_MARGIN = Math.pow(10, -10);

    /**
     * Constructor for point taking x,y coordinates.
     * @param x coordinate for point
     * @param y coordinate for point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other point to calculate distance to
     * @return distance between two points
     */
    public double distance(Point other) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other point to calculate if this point is equal to
     * @return true if balls are equal , else false
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.getX()) < ERROR_MARGIN && Math.abs(this.y - other.getY()) < ERROR_MARGIN;
    }

    /**
     * Return the x value of this point.
     *
     * @return this point x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return this point y value
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set the value of given x to this point's x value.
     *
     * @param x given x value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set the value of given y to this point's x value.
     *
     * @param y given y value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * check if a point is in x,y ranges of 2 other points.
     *
     * @param p1 point to check with
     * @param p2 point to check with
     * @return if point is between coordinate ranges return true
     */
    public boolean isPointBetween(Point p1, Point p2) {
        double maxX = Math.max(p1.getX(), p2.getX());
        double maxY = Math.max(p1.getY(), p2.getY());
        double minX = Math.min(p1.getX(), p2.getX());
        double minY = Math.min(p1.getY(), p2.getY());
        return (this.getX() >= minX) && (this.getX() <= maxX) && (this.getY() >= minY) && (this.getY() <= maxY);
    }
}

