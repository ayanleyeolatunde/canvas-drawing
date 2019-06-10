package com.deutsch.drawing.validators;


import com.deutsch.drawing.domain.Point;
import com.deutsch.drawing.exceptions.InvalidShapeException;
import com.deutsch.drawing.shapes.Line;
import com.deutsch.drawing.shapes.Shape;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RectangleValidatorUnitTest {

    private RectangleValidator sut;

    @Before
    public void before() {
        sut = new RectangleValidator();
    }


    @Test(expected = IllegalArgumentException.class)
    public void isValidRectangleWillThrowExceptionForNullShape() {

        // test fixtures
        final Shape shape = null;

        // when
        sut.isValidRectangle(shape);
    }

    @Test
    public void isValidRectangleWillThrowExceptionIfShapeIsAVerticalLine() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(7).withY(1).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Shape line = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        // when
        try {
            sut.isValidRectangle(line);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(InvalidShapeException.class);
        }
    }

    @Test
    public void isValidRectangleWillThrowExceptionIfShapeIsAHorizontalLine() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(1).withY(3).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Shape line = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        // when
        try {
            sut.isValidRectangle(line);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(InvalidShapeException.class);
        }
    }

    @Test
    public void isValidRectangleWillThrowExceptionIfShapeIsAPoint() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(7).withY(1).build();
        Point point2 = Point.Builder.create().withX(7).withY(1).build();
        final Shape line = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        // when
        try {
            sut.isValidRectangle(line);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(InvalidShapeException.class);
        }
    }

    @Test
    public void isValidRectangleWillDoNothingForAValidRectangle() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(15).withY(2).build();
        Point point2 = Point.Builder.create().withX(20).withY(5).build();
        final Shape line = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        // when
        sut.isValidRectangle(line);
    }
}