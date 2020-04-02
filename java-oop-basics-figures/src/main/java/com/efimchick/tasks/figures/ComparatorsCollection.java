package com.efimchick.tasks.figures;

import static java.lang.Math.abs;

public class ComparatorsCollection {

    public static int compareByArea(Figure lhs, Figure rhs) {
        double epsilon = 0.00001;
        if (abs(lhs.area() - rhs.area()) < epsilon) {
            return 0;
        }
        if (lhs.area() < rhs.area()) {
            return -1;
        }
        return 1;
    }

    public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs) {
        double epsilon = 0.00001;
        if (abs(lhs.leftmost().getX() - rhs.leftmost().getX()) < epsilon) {
            return 0;
        }
        if (lhs.leftmost().getX() < rhs.leftmost().getX()) {
            return -1;
        }
        return 1;
    }

    public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs){
        double epsilon = 0.00001;
        if (abs(lhs.centroid().getX() - rhs.centroid().getX()) < epsilon) {
            return 0;
        }
        if (lhs.centroid().getX() < rhs.centroid().getX()) {
            return -1;
        }
        return 1;
    }
}
