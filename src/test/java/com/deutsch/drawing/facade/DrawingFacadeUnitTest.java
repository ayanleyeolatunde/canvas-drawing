package com.deutsch.drawing.facade;

import com.deutsch.drawing.domain.Canvas;
import com.deutsch.drawing.exceptions.InvalidShapeException;
import com.deutsch.drawing.services.ShapeGenerator;
import com.deutsch.drawing.shapes.Line;
import com.deutsch.drawing.shapes.Shape;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DrawingFacadeUnitTest {

    private DrawingFacade sut;

    @Mock
    private Canvas canvas;

    @Mock
    private ShapeGenerator shapeGenerator;

    @Before
    public void before() {
        sut = new DrawingFacade(shapeGenerator, canvas);
        ReflectionTestUtils.setField(sut, "canvas", canvas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawWillThrowExceptionForNullInputString() {

        // test fixtures
        final String input = null;

        // when
        sut.draw(input);
    }

    @Test
    public void drawWillDoNothingWhenInputIsInvalid() {

        // test fixtures
        final String input = "L F 1 3";

        // when
        sut.draw(input);

        verifyZeroInteractions(canvas, shapeGenerator);
    }

    @Test
    public void drawWillNotDrawShapesWithoutACanvas() {

        // test fixtures
        final String input = "L 7 1 7 3";

        // test harness
        int unintializedCanvasState = -1;

        // given
        given(canvas.getWidth()).willReturn(unintializedCanvasState);

        // when
        sut.draw(input);

        verifyZeroInteractions(shapeGenerator);
    }

    @Test
    public void drawWillClearCanvasAndSetNewDimensionIfInputIsACanvasObject() {

        // test fixtures
        final String input = "C 20 5";

        // test harness
        int unintializedCanvasState = -1;

        // given
        given(canvas.getWidth()).willReturn(unintializedCanvasState);

        // when
        sut.draw(input);

        // then
        String[] canvasProperties = input.split(" ");

        verify(canvas, times(1))
                .resetCanvas(Integer.valueOf(canvasProperties[1]), Integer.valueOf(canvasProperties[2]));
        verify(canvas, times(1)).getWidth();
        verify(canvas, times(1)).draw();

        verifyNoMoreInteractions(canvas);
        verifyZeroInteractions(shapeGenerator);
    }

    @Test
    public void drawWillAddNewShapeToAnExistingCanvas() {

        // test fixtures
        final String input = "L 7 1 7 3";


        // test harness
        final Shape line = Line.Builder.create().build();

        // given
        given(canvas.getWidth()).willReturn(20);
        given(shapeGenerator.generateShape(anyString())).willReturn(line);

        // when
        sut.draw(input);

        // then
        verify(shapeGenerator, only()).generateShape(input);
        verify(canvas, times(1)).addShape(same(line));
        verify(canvas, times(1)).getWidth();
        verify(canvas, times(1)).draw();

        verifyNoMoreInteractions(canvas);
    }

    @Test
    public void drawWillNotAddNorDrawShapeIfShapeIsInvalid() {

        // test fixtures
        final String input = "L 7 1 4 3";

        // given
        given(canvas.getWidth()).willReturn(20);
        doThrow(new InvalidShapeException()).when(shapeGenerator).generateShape(anyString());

        // when
        sut.draw(input);

        // then
        verify(shapeGenerator, only()).generateShape(input);
        verify(canvas, only()).getWidth();
    }
}