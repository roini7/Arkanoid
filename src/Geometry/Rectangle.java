package Geometry;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Class rectangle defines a rectangle by a starting point(upper left),
 * width, height, side lines and color of rectangle.
 */
public class Rectangle {
    private double width, height;
    private Point upperLeft;
    private Line bLine1, bLine2, aLine1, aLine2;
    private Color color = Color.GRAY;

    /**
     * Create a new rectangle with a starting point,width and height.
     *
     * @param upperLeft is the starting point of the rectangle
     * @param width     is the width of the rectangle
     * @param height    is the height of the rectangle
     * @param color     is the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        /*Creating side lines of the rectangle*/
        this.bLine1 = new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.bLine2 = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.aLine1 = new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX(), upperLeft.getY() + height));
        this.aLine2 = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.color = color;

    }

    /**
     * Create a new rectangle with a starting point,width and height(without color).
     *
     * @param upperLeft is the starting point of the rectangle
     * @param width     is the width of the rectangle
     * @param height    is the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        /*Creating side lines of the rectangle*/
        this.bLine1 = new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.bLine2 = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.aLine1 = new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX(), upperLeft.getY() + height));
        this.aLine2 = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));

    }

    /**
     * Get color of rectangle.
     *
     * @return color of rectangle
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set color of rectangle.
     *
     * @param color color for rectangle
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * get first baseline of rectangle.
     *
     * @return baseline of rectangle
     */
    public Line getbLine1() {
        return bLine1;
    }

    /**
     * get second baseline of rectangle.
     *
     * @return baseline of rectangle
     */
    public Line getbLine2() {
        return bLine2;
    }

    /**
     * get first adjacent line of rectangle.
     *
     * @return adjacent line of rectangle
     */
    public Line getaLine1() {
        return aLine1;
    }

    /**
     * get second adjacent line of rectangle.
     *
     * @return adjacent line of rectangle
     */
    public Line getaLine2() {
        return aLine2;
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line is a line to check intersection points with
     * @return list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionList = new ArrayList<>();
        if (line.isIntersecting(bLine1)) {
            intersectionList.add(line.intersectionWith(bLine1));
        }
        if (line.isIntersecting(bLine2)) {
            intersectionList.add(line.intersectionWith(bLine2));
        }
        if (line.isIntersecting(aLine1)) {
            intersectionList.add(line.intersectionWith(aLine1));
        }
        if (line.isIntersecting(aLine2)) {
            intersectionList.add(line.intersectionWith(aLine2));
        }
        return intersectionList;
    }

    /**
     * Getter for width.
     *
     * @return width of rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for height.
     *
     * @return height of rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upper-left point of rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set upper-Left of rectangle to new point and move sides with it.
     *
     * @param point new point to set upper-left of rectangle to
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
        this.bLine1 = new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.bLine2 = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        this.aLine1 = new Line(new Point(upperLeft.getX(), upperLeft.getY()),
                new Point(upperLeft.getX(), upperLeft.getY() + height));
        this.aLine2 = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                new Point(upperLeft.getX() + width, upperLeft.getY() + height));
    }
}
