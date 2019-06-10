package com.deutsch.drawing.factory;

import com.deutsch.drawing.services.ShapeGenerator;
import com.deutsch.drawing.domain.Canvas;
import com.deutsch.drawing.facade.DrawingFacade;
import com.deutsch.drawing.validators.LineValidator;
import com.deutsch.drawing.validators.RectangleValidator;

public class DrawingSingletonFactory {

    public static final int UNINITIALIZED_CANVAS_STATE_DIMENSION = -1;

    private static RectangleValidator rectangleValidator;
    private static LineValidator lineValidator;
    private static DrawingFacade drawingFacade;
    private static ShapeGenerator shapeGenerator;
    private static Canvas canvas;

    public static DrawingFacade getDrawingFacade() {

        if (drawingFacade == null) {

            drawingFacade = new DrawingFacade(getShapeGenerator(), getCanvas());
        }
        return drawingFacade;
    }

    private static ShapeGenerator getShapeGenerator() {

        if (shapeGenerator == null) {

            shapeGenerator = new ShapeGenerator(getLineValidator(), getRectangleValidator());
        }
        return shapeGenerator;
    }

    private static RectangleValidator getRectangleValidator() {

        if (rectangleValidator == null) {

            rectangleValidator = new RectangleValidator();
        }
        return rectangleValidator;
    }

    private static LineValidator getLineValidator() {

        if (lineValidator == null) {

            lineValidator = new LineValidator();
        }
        return lineValidator;
    }

    private static Canvas getCanvas() {

        if (canvas == null) {
            canvas = new Canvas(UNINITIALIZED_CANVAS_STATE_DIMENSION, UNINITIALIZED_CANVAS_STATE_DIMENSION);
        }
        return canvas;
    }

}
