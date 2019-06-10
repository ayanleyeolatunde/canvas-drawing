package com.deutsch.drawing.validators;

import com.deutsch.drawing.exceptions.InvalidShapeException;
import com.deutsch.drawing.shapes.Shape;

import javax.annotation.Nonnull;

import static com.deutsch.drawing.utils.Utils.notNull;

public class LineValidator {

    public void isValidLine(@Nonnull Shape shape)  {

        notNull(shape, "shape cannot be  null");

        boolean isValidLine = (shape.getPoint1().getX() == shape.getPoint2().getX()
                && shape.getPoint1().getY() != shape.getPoint2().getY())
                || (shape.getPoint1().getY() == shape.getPoint2().getY()
                && shape.getPoint1().getX() != shape.getPoint2().getX());

        if (!isValidLine) {
            throw new InvalidShapeException();
        }
    }
}
