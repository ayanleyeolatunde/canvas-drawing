package com.deutsch.drawing.domain;

import com.deutsch.drawing.shapes.Line;
import com.deutsch.drawing.shapes.Shape;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CanvasUnitTest {

    private Canvas sut;

    @Before
    public void before() {
        sut = new Canvas(20, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShapeWillThrowExceptionForNullShape() {

        // test fixtures
        final Shape shape = null;

        // when
        sut.addShape(shape);
    }

    @Test
    public void addShapeWillAddShapeToCanvasAndUpdateDrawing() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(1).withY(3).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Shape shape = new Line(point1, point2);

        //when
        sut.addShape(shape);

        // then
        final String expectedDrawing = "" +
                "----------------------\n" +
                "|                    |\n" +
                "|                    |\n" +
                "|xxxxxxx             |\n" +
                "|                    |\n" +
                "|                    |\n" +
                "----------------------\n";

        final List<Shape> expectedShapes = Collections.singletonList(shape);

        assertThat(sut.getDrawing()).isEqualTo(expectedDrawing);
        assertThat(sut.getShapes()).isEqualTo(expectedShapes);
    }

    @Test
    public void resetCanvasWillRemoveShapesAndResetCanavasToNewDimension() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(1).withY(3).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Shape shape = new Line(point1, point2);
        sut.addShape(shape);

        //when
        sut.resetCanvas(10, 4);

        // then
        assertThat(sut.getShapes()).isEmpty();
        assertThat(sut.getWidth()).isEqualTo(10);
        assertThat(sut.getHeight()).isEqualTo(4);
    }
}