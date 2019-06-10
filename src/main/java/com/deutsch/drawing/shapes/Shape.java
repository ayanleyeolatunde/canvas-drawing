package com.deutsch.drawing.shapes;

import com.deutsch.drawing.domain.Point;

import javax.annotation.Nonnull;

public abstract class Shape {

    protected Point point1;
    protected Point point2;

    public Shape(@Nonnull Point point1, @Nonnull Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }
}
