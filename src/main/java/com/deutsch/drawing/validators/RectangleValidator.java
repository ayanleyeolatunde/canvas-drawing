package com.deutsch.drawing.validators;

import com.deutsch.drawing.exceptions.InvalidShapeException;
import com.deutsch.drawing.shapes.Shape;

import javax.annotation.Nonnull;

import static com.deutsch.drawing.utils.Utils.notNull;

public class RectangleValidator {

    public void isValidRectangle(@Nonnull Shape shape) {

        notNull(shape, "shape cannot be  null");

        if (isAline(shape) || isPoint(shape))
            throw new InvalidShapeException();
    }

    // Private methods

    private boolean isPoint(@Nonnull Shape shape) {
        return shape.getPoint1().equals(shape.getPoint2());
    }

    private boolean isAline(@Nonnull Shape shape) {

        return (shape.getPoint1().getX() == shape.getPoint2().getX()
                && shape.getPoint1().getY() != shape.getPoint2().getY())
                || (shape.getPoint1().getY() == shape.getPoint2().getY()
                && shape.getPoint1().getX() != shape.getPoint2().getX());
    }
}
