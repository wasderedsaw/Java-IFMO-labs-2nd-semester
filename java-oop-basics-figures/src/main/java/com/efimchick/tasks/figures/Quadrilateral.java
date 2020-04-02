package com.efimchick.tasks.figures;


import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class Quadrilateral extends Figure{ //bad area, bad centroid, bad constructor
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) { // both diagonals should be inside of the quad
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException("Points can not be null");
        }
        Triangle t1 = new Triangle(a, b, c);
        Triangle t2 = new Triangle(b, c, d);
        Triangle t3 = new Triangle(c, d, a);
        Triangle t4 = new Triangle(d, a, b);
        //ax + by + c = 0; 1 stands for AC, 2 for BD
        double a1 = a.getY() - c.getY();
        double b1 = c.getX() - a.getX();
        double c1 = a.getX() * c.getY() - a.getY() * c.getX();
        double a2 = b.getY() - d.getY();
        double b2 = d.getX() - b.getX();
        double c2 = b.getX() * d.getY() - b.getY() * d.getX();
        if (!((a1 * b.getX() + b1 * b.getY() + c1) * (a1 * d.getX() + b1 * d.getY() + c1) < 0
                && (a2 * a.getX() + b2 * a.getY() + c2) * (a2 * c.getX() + b2 * c.getY() + c2) < 0)) {
            throw new IllegalArgumentException("These points do not make a convex quadrilateral");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Point getD() {
        return d;
    }

    @Override
    public double area() {
        double x1 = a.getX() - c.getX();
        double y1 = a.getY() - c.getY();
        double x2 = b.getX() - d.getX();
        double y2 = b.getY() - d.getY();
        return 0.5 * abs(x1 * y2 - x2 * y1);
    }

    @Override
    public Point centroid() {
        Point gA = new Triangle(b, c, d).centroid();
        Point gC = new Triangle(d, a, b).centroid();
        Point gB = new Triangle(c, d, a).centroid();
        Point gD = new Triangle(a, b, c).centroid();
        double x1 = gA.getX(); double y1 = gA.getY();
        double x2 = gC.getX(); double y2 = gC.getY();
        double x3 = gB.getX(); double y3 = gB.getY();
        double x4 = gD.getX(); double y4 = gD.getY();
        double x = ((x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4)) /
                ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
        double y = ((x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4)) /
                ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
        return new Point(x, y);
    }

    @Override
    public Point leftmost() {
        if (a.getX() <= b.getX() && a.getX() <= c.getX() && a.getX() <= d.getX()) {
            return a;
        }
        if (b.getX() <= a.getX() && b.getX() <= c.getX() && b.getX() <= d.getX()) {
            return b;
        }
        if (c.getX() <= a.getX() && c.getX() <= b.getX() && c.getX() <= d.getX()) {
            return c;
        }
        return d;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure == this) {
            return true;
        }
        if (figure == null || figure.getClass() != this.getClass()) {
            return false;
        }
        Quadrilateral copy = (Quadrilateral) figure;
        Triangle temp = new Triangle(copy.getB(), copy.getC(), copy.getD());
        if (a.isTheSame(copy.getA())) {
            Triangle helper = new Triangle(b, c, d);
            return helper.isTheSame(temp);
        }
        if (b.isTheSame(copy.getA())) {
            Triangle helper = new Triangle(a, c, d);
            return helper.isTheSame(temp);
        }
        if (c.isTheSame(copy.getA())) {
            Triangle helper = new Triangle(a, b, d);
            return helper.isTheSame(temp);
        }
        if (d.isTheSame(copy.getA())) {
            Triangle helper = new Triangle(a, b, c);
            return helper.isTheSame(temp);
        }
        return false;
    }

    public String toString() {
        return "Quadrilateral[" + a.toString() + b.toString() + c.toString() + d.toString() + "]";
    }
}
