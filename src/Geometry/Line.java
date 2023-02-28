package Geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Class defining line and its attributes.
 */
public class Line {
    private final Point start, end;
    private final double x1, x2, y1, y2;

    /**
     * Constructor for line taking starting and ending point.
     *
     * @param start starting point of line
     * @param end   ending point of line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
    }

    /**
     * Constructor for line taking (x,y) coordinates for starting point and (x,y) coordinates for ending point.
     *
     * @param x1 x coordinate for starting point
     * @param y1 y coordinate for starting point
     * @param x2 x coordinate for ending point
     * @param y2 y coordinate for ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate length of line.
     *
     * @return length of line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * calculate middle point of line.
     *
     * @return middle point of line
     */
    public Point middle() {
        double x = (x1 + x2) / 2;
        double y = (y1 + y2) / 2;
        return new Point(x, y);
    }

    /**
     * get start point of line.
     *
     * @return start point of line
     */
    public Point start() {
        return this.start;
    }

    /**
     * get end point of line.
     *
     * @return end point of line
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if line is vertical.
     *
     * @return if line is vertical return true, else return false
     */
    public boolean isVertical() {
        return this.x1 == this.x2;
    }

    /**
     * check if line is horizontal.
     *
     * @return if line is horizontal return true, else return false
     */
    public boolean isHorizontal() {
        return this.y1 == this.y2;
    }

    /**
     * calculate line's incline.
     *
     * @return line's incline
     */
    public double lineIncline() {
        //check division by zero
        if (this.x2 == this.x1) {
            return 0;
        }
        return (this.y2 - this.y1) / (this.x2 - this.x1);
    }

    /**
     * calculate for line formula 'f(x) = Ax + b' the 'b' value of the expression.
     *
     * @return 'b' value of line
     */
    public double lineVar() {
        return (this.y1 - (this.lineIncline() * this.x1));
    }

    /**
     * calculate estimated intersection of two lines.
     *
     * @param other line to calculate intersection with
     * @return intersection point
     */
    public Point estIntersect(Line other) {
        Point intersectP;
        /*check if lines are equal*/
        if (this.equals(other)) {
            return null;
            /*x coordinate for vertical line will be intersection point*/
        } else if (this.isVertical() && other.isHorizontal()) {
            return new Point(this.x1, other.y1);
        } else if (other.isVertical() && this.isHorizontal()) { //same but now other is vertical
            return new Point(other.x1, this.y1);
            /*this is vertical but other isn't vertical and horizontal*/
        } else if (this.isVertical() && (!other.isVertical() && !other.isHorizontal())) {
            double y = (other.lineIncline() * this.x1) + other.lineVar();
            return new Point(this.x1, y);
            /*same but now other is vertical and this isn't vertical and horizontal*/
        } else if (other.isVertical() && (!this.isVertical() && !this.isHorizontal())) {
            double y = (this.lineIncline() * other.x1) + this.lineVar();
            return new Point(other.x1, y);
            /*this is horizontal but other isn't vertical and horizontal*/
        } else if (this.isHorizontal() && (!other.isHorizontal() && !other.isVertical())) {
            double x = (this.y1 - other.lineVar()) / other.lineIncline();
            return new Point(x, this.y1);
            /*same but this time other is horizontal and this isn't horizontal and vertical*/
        } else if (other.isHorizontal() && (!this.isHorizontal() && !this.isVertical())) {
            double x = (other.y1 - this.lineVar()) / this.lineIncline();
            return new Point(x, other.y1);
        }
        /*check if lines touch and return 'touching' point if they do*/
        if (touchingPoint(other) != null) {
            return touchingPoint(other);
            /*if line is contained then there is no intersection*/
        } else if (this.isContained(other)) {
            return null;
            /*lines aren't vertical or horizontal*/
        } else {
            double vars = Math.abs((this.lineVar() - other.lineVar()));
            double inclines = Math.abs((this.lineIncline() - other.lineIncline()));
            double x = vars / inclines;
            double y = (this.lineIncline() * x) + this.lineVar();
            intersectP = new Point(x, y); //evaluate intersection for 2 none horizontal\vertical lines
            return intersectP; // return intersection point
        }
    }

    /**
     * a method to check if one line contains the other.
     *
     * @param other line to check if one line contains
     * @return if line is contained in other line - true , else false
     */
    public boolean isContained(Line other) {
        if (this.isVertical() && other.isVertical()) { // if this line is completely contained in other
            if (((Math.min(this.start.getY(), this.end.getY()) > Math.min(other.start.getY(), other.end.getY()))
                    && Math.max(this.start.getY(), this.end.getY()) < Math.max(other.start.getY(), other.end.getY()))
                    || ((Math.min(other.start.getY(), other.end.getY()) > Math.min(this.start.getY(), this.end.getY()))
                    && Math.max(other.start.getY(), other.end.getY()) < Math.max(this.start.getY(), this.end.getY()))) {
                return true;
            }
            /*if this line is not fully contained in other*/
            if ((this.start.getY() < Math.max(other.start.getY(), other.end.getY())
                    && this.start.getY() > Math.min(other.start.getY(), other.end.getY()))
                    || (this.end.getY() < Math.max(other.start.getY(), other.end.getY())
                    && this.end.getY() > Math.min(other.start.getY(), other.end.getY()))) {
                return true;
            }
        }
        if (this.lineIncline() == other.lineIncline() && this.lineVar() == other.lineVar()) {
            /*if this line is fully contained in other line*/
            if (this.start.isPointBetween(other.start, other.end) || this.end.isPointBetween(other.start, other.end)
                    || other.start.isPointBetween(this.start, this.end)
                    || other.end.isPointBetween(this.start, this.end)) {
                return true;
            }
            return this.start.isPointBetween(other.start, other.end) || this.end.isPointBetween(other.start, other.end);
        }
        return false;
    }

    /**
     * find touching point of lines.
     *
     * @param other line to check if both lines touch
     * @return touching point
     */
    public Point touchingPoint(Line other) {
        /*check for 'this' line start point if lines touch*/
        if (this.start.equals(other.start) && (this.end.distance(other.end) == this.length() + other.length())
                || (this.start.equals(other.end)
                && (this.end.distance(other.start) == this.length() + other.length()))) {
            return start;
            /*check for 'this' line end point if lines touch*/
        } else if (this.end.equals(other.start) && this.start.distance(other.end) == this.length() + other.length()
                || this.end.equals(other.end) && this.start.distance(other.start) == this.length() + other.length()) {
            return end;
            /*return null if lines don't touch*/
        } else {
            return null;
        }
    }

    /**
     * check if two given lines are intersecting.
     *
     * @param other line to check if this line is intersecting with
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        /*check if both lines are vertical or horizontal*/
        if (((this.isVertical() && other.isVertical() && (this.x1 != other.x1)))
                || ((this.isHorizontal() && other.isHorizontal()) && (this.y1 != other.y1))) {
            return false;
        }
        if (this.isContained(other)) {
            return true;
        }
        if (!this.isHorizontal() && !this.isVertical() && !other.isHorizontal() && !other.isVertical()) {
            /*else check if lines incline is equal*/
            if ((this.lineIncline() == other.lineIncline()) && (!this.start.equals(other.start))
                    && (!this.start.equals(other.end)
                    && !this.end.equals(other.start) && !this.end.equals(other.end))) {
                return false;
            }
        }
        /*both lines are equal*/
        if (this.equals(other)) {
            return true;
        }
        /*lines 'touch' in a single point*/
        if (this.start.equals(other.end) || this.start.equals(other.start)
                || this.end.equals(other.start) || this.end.equals(other.end)) {
            return true;
        }

        /*otherwise, lines could intersect, check for estimated intersection point with estIntersect method*/
        Point intersectP = this.estIntersect(other);
        /*check if lines lengths actually intersect given estimated intersection point*/
        return (this.length() >= intersectP.distance(this.start)) && (this.length() >= intersectP.distance(this.end))
                && (other.length() >= intersectP.distance(other.start))
                && (other.length() >= intersectP.distance(other.end));
    }

    /**
     * check if two lines are intersecting and calculate intersection point if they do.
     *
     * @param other line to if this line is intersecting with
     * @return Returns the intersection point if the lines intersect,and null otherwise
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) { //check if lines are intersecting
            return this.estIntersect(other); //return intersection point
        } else {
            return null; // return null if lines aren't intersecting
        }
    }

    /**
     * a method to check if two lines are identical.
     *
     * @param other line to check if this line is equal to
     * @return return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start)) && (this.end.equals(other.end)))
                || (this.start.equals(other.end) && this.end.equals(other.start)
                || (other.start.equals(this.start) && other.end.equals(this.end))
                || (other.start.equals(this.end) && other.end.equals(this.start)));
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect rectangle to calculate closest intersection point with
     * @return closest intersection point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        int flag = 0;
        List<Point> intersectPoints = rect.intersectionPoints(this);
        List<Point> updatedIntersections = new ArrayList<>();
        /*create an array of intersection points with rectangle*/
        for (Point point : intersectPoints) {
            if (point != null) {
                updatedIntersections.add(point);
                flag = 1;
            }
        }
        /*if none was added to intersection list return null*/
        if (flag != 1) {
            return null;
        } else {
            Point closestPoint = updatedIntersections.get(0);
            /*find closest' point in intersection list with start point of line*/
            for (int i = 1; i < updatedIntersections.size(); i++) {
                if (closestPoint.distance(this.start()) > updatedIntersections.get(i).distance(this.start)) {
                    closestPoint = updatedIntersections.get(i);
                }
            }
            return closestPoint; //return the closest' point
        }
    }
}
