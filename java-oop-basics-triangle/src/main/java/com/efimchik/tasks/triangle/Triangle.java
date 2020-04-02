package com.efimchik.tasks.triangle;

import static java.lang.Math.abs;

class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;


    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException("Points can not be null");
        }
        double epsilon = 0.00001;
        double x1 = b.getX() - a.getX();
        double x2 = c.getX() - a.getX();
        double y1 = b.getY() - a.getY();
        double y2 = c.getY() - a.getY();
        double area = abs(x1 * y2 - x2 * y1) / 2;
        if (area < epsilon) {
            throw new IllegalArgumentException("Points should make a triangle");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double area() {
        double x1 = b.getX() - a.getX();
        double x2 = c.getX() - a.getX();
        double y1 = b.getY() - a.getY();
        double y2 = c.getY() - a.getY();
        return abs(x1 * y2 - x2 * y1) / 2;
    }

    public Point centroid(){
        double x = (a.getX() + b.getX() + c.getX()) / 3;
        double y = (a.getY() + b.getY() + c.getY()) / 3;
        return new Point(x, y);
    }

    public static void main(String[] args) {
        Point a = new Point(-1, 0);
        Point b = new Point(0, -1);
        Point c = new Point(0, 0);
        Triangle trig = new Triangle(a, b, c);
        System.out.println(trig.area());
        System.out.println(trig.centroid().getX());
        System.out.println(trig.centroid().getY());
    }
}
