package com.deutsch.drawing.domain;

import com.deutsch.drawing.shapes.Shape;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static com.deutsch.drawing.utils.Utils.notNull;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Canvas {

    int width;
    int height;
    private StringBuilder drawing;
    private final List<Shape> shapes = new ArrayList<>();

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        drawing = new StringBuilder();
        updateCanvas();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Nonnull
    public String getDrawing() {
        return drawing.toString();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void draw() {
        System.out.println(drawing.toString());
    }

    public void resetCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        shapes.clear();
        updateCanvas();
    }

    public void addShape(@Nonnull Shape shape) {

        notNull(shape, "shape cannot be null");

        shapes.add(shape);
        updateCanvas();
    }

    // Private methods

    private void updateCanvas() {

        boolean shapePosition;

        drawing.setLength(0);

        for (int i = 0; i <= this.height + 1; i++) {
            for (int j = 0; j <= this.width + 1; j++) {
                shapePosition = false;
                for (Shape shape : shapes) {
                    int y1 = shape.getPoint1().getY();
                    int y2 = shape.getPoint2().getY();
                    int x1 = shape.getPoint1().getX();
                    int x2 = shape.getPoint2().getX();
                    int minHeight = min(y1, y2);
                    int maxHeight = max(y1, y2);
                    int minWidth = min(x1, x2);
                    int maxWidth = max(x1, x2);

                    if (((j == minWidth || j == maxWidth) && (i >= minHeight && i <= maxHeight)) ||
                            ((i == minHeight || i == maxHeight) && (j > minWidth && j < maxWidth))) {
                        drawing.append("x");
                        shapePosition = true;
                        break;
                    }
                }
                if (!shapePosition) {
                    generateCanvasPixel(i, j);
                }
            }
            drawing.append("\n");
        }
    }

    private void generateCanvasPixel(int i, int j) {

        if (i == 0 || i == height + 1)
            drawing.append("-");
        else if (j == 0 || j == width + 1) {
            drawing.append("|");
        } else
            drawing.append(" ");
    }
}


