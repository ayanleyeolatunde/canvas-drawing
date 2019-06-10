package com.deutsch.drawing.services;

import com.deutsch.drawing.domain.Point;
import com.deutsch.drawing.shapes.Line;
import com.deutsch.drawing.shapes.Rectangle;
import com.deutsch.drawing.shapes.Shape;
import com.deutsch.drawing.validators.LineValidator;
import com.deutsch.drawing.validators.RectangleValidator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.deutsch.drawing.utils.Utils.notNull;

public class ShapeGenerator {

    private LineValidator lineValidator;
    private RectangleValidator rectangleValidator;

    public ShapeGenerator(@Nonnull LineValidator lineValidator, @Nonnull RectangleValidator rectangleValidator) {
        this.lineValidator = lineValidator;
        this.rectangleValidator = rectangleValidator;
    }

    @Nullable
    public Shape generateShape(@Nonnull String input) {

        notNull(input, "input cannot be null");

        final String[] shapeParameters = input.split(" ");
        Point point1;
        Point point2;
        Shape shape = null;

        switch (shapeParameters[0]) {

            case "l":
            case "L":

                point1 = getFirstPoint(shapeParameters);
                point2 = getSecondPoint(shapeParameters);
                Line line = Line.Builder.create()
                        .withPoint1(point1)
                        .withPoint2(point2)
                        .build();

                lineValidator.isValidLine(line);
                shape = line;

                break;

            case "r":
            case "R":

                point1 = getFirstPoint(shapeParameters);
                point2 = getSecondPoint(shapeParameters);
                Rectangle rectangle = Rectangle.Builder.create()
                        .withPoint1(point1)
                        .withPoint2(point2)
                        .build();
                rectangleValidator.isValidRectangle(rectangle);
                shape = rectangle;

                break;

        }
        return shape;
    }

    // Private methods

    private Point getSecondPoint(@Nonnull String[] shapeParameters) {

        return Point.Builder.create()
                .withX(Integer.valueOf(shapeParameters[3]))
                .withY(Integer.valueOf(shapeParameters[4]))
                .build();
    }

    private Point getFirstPoint(@Nonnull String[] shapeParameters) {

        return Point.Builder.create()
                .withX(Integer.valueOf(shapeParameters[1]))
                .withY(Integer.valueOf(shapeParameters[2]))
                .build();
    }
}
