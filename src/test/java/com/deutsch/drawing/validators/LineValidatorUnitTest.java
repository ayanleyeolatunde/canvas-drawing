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
public class LineValidatorUnitTest {

    private LineValidator sut;

    @Before
    public void before() {
        sut = new LineValidator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValidLineWillThrowExceptionForNullShape() {

        // test fixtures
        final Shape shape = null;

        // when
        sut.isValidLine(shape);
    }

    @Test
    public void isValidWillThrowExceptionForInvalidLine() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(4).withY(1).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Shape line = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        // when
        try {
            sut.isValidLine(line);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(InvalidShapeException.class);
        }
    }

    @Test
    public void isValidWillDoNothingForValidVerticalLine() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(7).withY(1).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Shape line = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        // when
        sut.isValidLine(line);
    }

    @Test
    public void isValidWillDoNothingForValidHorizontalLine() {

        // test fixtures
        Point point1 = Point.Builder.create().withX(1).withY(3).build();
        Point point2 = Point.Builder.create().withX(7).withY(3).build();
        final Shape line = Line.Builder.create().withPoint1(point1).withPoint2(point2).build();

        // when
        sut.isValidLine(line);
    }
}