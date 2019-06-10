package com.deutsch.drawing.services;

import com.deutsch.drawing.domain.Point;
import com.deutsch.drawing.shapes.Line;
import com.deutsch.drawing.shapes.Rectangle;
import com.deutsch.drawing.shapes.Shape;
import com.deutsch.drawing.validators.LineValidator;
import com.deutsch.drawing.validators.RectangleValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ShapeGeneratorUnitTest {

    private ShapeGenerator sut;

    @Mock
    private LineValidator lineValidator;

    @Mock
    private RectangleValidator rectangleValidator;

    @Before
    public void before() {
        sut = new ShapeGenerator(lineValidator, rectangleValidator);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateShapeWillThrowExceptionForNullInput() {

        // test fixtures
        final String input = null;

        // when
        sut.generateShape(input);
    }

    @Test
    public void generateShapeWillGenerateLineShapeForUpperCaseL() {

        // test fixtures
        final String input = "L 7 1 7 3";

        // when
        Shape result = sut.generateShape(input);

        // then
        Point point1 = Point.Builder.create().withX(7).withY(1).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Line expectedLine = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        assertThat(result).isEqualTo(expectedLine);
    }

    @Test
    public void generateShapeWillGenerateLineShapeForLowerCaseL() {

        // test fixtures
        final String input = "l 7 1 7 3";

        // when
        Shape result = sut.generateShape(input);

        // then
        Point point1 = Point.Builder.create().withX(7).withY(1).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Line expectedLine = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        assertThat(result).isEqualTo(expectedLine);
    }

    @Test
    public void generateShapeWillGenerateRectangleShapeForLowerCaseR() {

        // test fixtures
        final String input = "R 15 2 20 5";

        // when
        Shape result = sut.generateShape(input);

        // then
        Point point1 = Point.Builder.create().withX(15).withY(2).build();
        Point point2 = Point.Builder.create().withX(20).withY(5).build();
        final Rectangle expectedRectangle = Rectangle.Builder.create().withPoint1(point1).withPoint2(point2).build();

        assertThat(result).isEqualTo(expectedRectangle);
    }

    @Test
    public void generateShapeWillGenerateRectangleShapeForLowerCaser() {

        // test fixtures
        final String input = "r 15 2 20 5";

        // when
        Shape result = sut.generateShape(input);

        // then
        Point point1 = Point.Builder.create().withX(15).withY(2).build();
        Point point2 = Point.Builder.create().withX(20).withY(5).build();
        final Rectangle expectedRectangle = Rectangle.Builder.create().withPoint1(point1).withPoint2(point2).build();

        assertThat(result).isEqualTo(expectedRectangle);
    }

    @Test
    public void generateShapeWillReturnNullIfShapeIsNotValid() {

        // test fixtures
        final String input = "K 15 2 20 5";

        // when
        Shape result = sut.generateShape(input);

        // then
        assertThat(result).isNull();
    }
}