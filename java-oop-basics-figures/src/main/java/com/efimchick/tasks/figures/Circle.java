package com.efimchick.tasks.figures;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.PI;

class Circle extends Figure {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        double epsilon = 0.00001;
        if (center == null) {
            throw new IllegalArgumentException("Center can not be null");
        }
        if (radius < epsilon) {
            throw new IllegalArgumentException("Radius should be a positive number");
        }
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double area() {
        return PI * pow(radius, 2);
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public Point leftmost() {
        return new Point(center.getX() - radius, center.getY());
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == this) {
            return true;
        }
        if (figure == null || figure.getClass() != this.getClass()) {
            return false;
        }
        Circle copy = (Circle) figure;
        double epsilon = 0.00001;
        return center.isTheSame(copy.getCenter()) && abs(radius - copy.getRadius()) < epsilon;
    }

    public String toString() {
        return "Circle[" + center.toString() + radius + "]";
    }
}
