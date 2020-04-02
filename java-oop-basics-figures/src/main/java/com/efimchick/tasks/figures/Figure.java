package com.efimchick.tasks.figures;

abstract class Figure{

    public abstract double area();

    public abstract Point centroid();

    public abstract boolean isTheSame(Figure figure);

    public abstract Point leftmost();

    public String toString() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) { // 1 failed test - wrong leftmost point in either quadrilateral or triangle
        Point a = new Point(0, 0);
        Point b = new Point(5, 0);
        Point c = new Point(5, 5);
        Point d = new Point(0, 5);
        double radius = 2.5;

        Triangle triangle = new Triangle(a, b, c);

        Quadrilateral quadrilateral = new Quadrilateral(a, b, c, d);

        Circle circle = new Circle(a, radius);

        System.out.println(triangle.leftmost());
        System.out.println(triangle);
        System.out.println(triangle.centroid());
        System.out.println(triangle.area());

        System.out.println(quadrilateral.leftmost());
        System.out.println(quadrilateral);
        System.out.println(quadrilateral.centroid());
        System.out.println(quadrilateral.area());

        System.out.println(circle.leftmost());
        System.out.println(circle);
        System.out.println(circle.centroid());
        System.out.println(circle.area());
    }
}
