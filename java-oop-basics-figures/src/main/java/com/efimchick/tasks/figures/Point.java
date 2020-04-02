package com.efimchick.tasks.figures;

import static java.lang.Math.abs;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isTheSame(Point other) {
        double epsilon = 0.00001;
        return abs(this.x - other.getX()) < epsilon && abs(this.y - other.getY()) < epsilon;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
