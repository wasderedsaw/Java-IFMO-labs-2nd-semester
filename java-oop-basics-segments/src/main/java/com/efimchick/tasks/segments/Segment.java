package com.efimchick.tasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Segment {

    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Points can not be null");
        }
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            throw new IllegalArgumentException("Start and end should not be the same point");
        }
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double length() {
        return sqrt(pow(end.getX() - start.getX(), 2) + pow(end.getY() - start.getY(), 2));
    }

    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    public boolean isOnSegment (Point another) {
        if (start.getX() == another.getX() && start.getY() == another.getY() || end.getX() == another.getX() && end.getY() == another.getY()) {
            return true;
        }
        Segment first = new Segment(another, start);
        Segment second = new Segment(another, end);
        double epsilon = 0.00001;
        return abs(first.length() + second.length() - this.length()) < epsilon;
    }

    public Point intersection(Segment another) { //Ax + By + C = 0
        double thisA = start.getY() - end.getY();
        double thisB = end.getX() - start.getX();
        double thisC = start.getX() * end.getY() - end.getX() * start.getY();
        double otherA = another.getStart().getY() - another.getEnd().getY();
        double otherB = another.getEnd().getX() - another.getStart().getX();
        double otherC = another.getStart().getX() * another.getEnd().getY() - another.getEnd().getX() * another.getStart().getY();
        double epsilon = 0.00001;
        double determinant = thisA * otherB - otherA * thisB;
        if (abs(determinant) < epsilon) { // parallel or same
            if (abs(thisC - otherC) < epsilon) { // same line
                if (abs(thisA) < epsilon) { // a vertical line, compare y's
                    double testing = (end.getY() - start.getY()) * (another.getEnd().getY() - another.getStart().getY());
                    if (abs(start.getY() - another.getStart().getY()) < epsilon) { // same start
                        if (testing < -epsilon) return start;
                    } else if (abs(start.getY() - another.getEnd().getY()) < epsilon) { // this.start = another.end
                        if (testing > epsilon) return start;
                    } else if (abs(end.getY() - another.getStart().getY()) < epsilon) { // this.end = another.start
                        if (testing > epsilon) return end;
                    } else if (abs(end.getY() - another.getEnd().getY()) < epsilon) { // same end
                        if (testing < -epsilon) return end;
                    }
                    return null;
                } else { // not a vertical line, compare x's
                    double testing = (end.getX() - start.getX()) * (another.getEnd().getX() - another.getStart().getX());
                    if (abs(start.getX() - another.getStart().getX()) < epsilon) { // same start
                        if (testing < -epsilon) return start;
                    } else if (abs(start.getX() - another.getEnd().getX()) < epsilon) { // this.start = another.end
                        if (testing > epsilon) return start;
                    } else if (abs(end.getX() - another.getStart().getX()) < epsilon) { // this.end = another.start
                        if (testing > epsilon) return end;
                    } else if (abs(end.getX() - another.getEnd().getX()) < epsilon) { // same end
                        if (testing < -epsilon) return end;
                    }
                    return null;
                }
            } else { // parallel lines
                return null;
            }
        } else { // not parallel
            Point answer = new Point((thisB * otherC - thisC * otherB) / determinant, (otherA * thisC - thisA * otherC) / determinant);
            if (isOnSegment(answer) && another.isOnSegment(answer)) {
                return answer;
            } else {
                return null;
            }
        }
    }
}