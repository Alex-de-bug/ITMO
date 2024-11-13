package com.example.web4.utils;

import com.example.web4.dto.Coordinates;

public class AreaCheck {
    public static boolean isHit(Coordinates coordinates) {
        return coordinates != null && isHit(coordinates.getX(), coordinates.getY(), coordinates.getR());
    }

    public static boolean isHit(double x, double y, double r) {
        return isCircleHit(x, y, r) || isRectangleHit(x, y, r) || isTriangleHit(x, y, r);
    }

    private static boolean isTriangleHit(double x, double y, double r) {
        return (x >= 0 && y <= 0) && (y >= x/2 - r/2);
    }

    private static boolean isRectangleHit(double x, double y, double r) {
        return (x >= 0 && y >= 0) && (x <= r && y <= r);
    }

    private static boolean isCircleHit(double x, double y, double r) {
        return (x <= 0 && y >= 0) && (x*x + y*y <= r*r/4);
    }
}
