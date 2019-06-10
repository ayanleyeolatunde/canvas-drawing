package com.deutsch.drawing.facade;

import com.deutsch.drawing.domain.Canvas;
import com.deutsch.drawing.exceptions.InvalidShapeException;
import com.deutsch.drawing.services.ShapeGenerator;

import javax.annotation.Nonnull;

import static com.deutsch.drawing.utils.Utils.notNull;

public class DrawingFacade {

    private static final String COMMAND_PATTERN = "((^(L|l|R|r)(\\s\\d+){4}$)|(^(C|c)(\\s\\d+){2}$))";

    private Canvas canvas;
    private ShapeGenerator shapeGenerator;

    public DrawingFacade(@Nonnull ShapeGenerator shapeGenerator, @Nonnull Canvas canvas) {
        this.shapeGenerator = shapeGenerator;
        this.canvas = canvas;
    }

    public void draw(@Nonnull String input) {

        notNull(input, "input cannot be null");

        if (invalidInput(input)) {
            System.out.println("invalid Input specified: " + input);
            return;
        } else if (canvas.getWidth() == -1 && !isCanvas(input)) {
            System.out.println("No canvas specified yet");
            return;
        } else if (isCanvas(input)) {
            resetCanvas(input);
        } else {
            try {
                canvas.addShape(shapeGenerator.generateShape(input));
            } catch (InvalidShapeException ex) {
                System.out.println("Invalid shape provided");
                return;
            }
        }
        canvas.draw();
    }

    // Private methods

    private void resetCanvas(@Nonnull String input) {

        String[] canvasProperties = input.split(" ");
        canvas.resetCanvas(Integer.valueOf(canvasProperties[1]), Integer.valueOf(canvasProperties[2]));
    }


    private boolean isCanvas(@Nonnull String input) {
        return input.matches("^(c|C).*");
    }

    private boolean invalidInput(@Nonnull String input) {

        return !input.matches(COMMAND_PATTERN);
    }
}
