package com.deutsch.drawing.utils;

import com.deutsch.drawing.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UtilsUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void notNullWithNullArgumentWillThrowAnIllegalArgumentException() {

        // deutsch fixtures
        final Object input = null;
        final String message = null;

        // when
        Utils.notNull(input, message);
    }

    @Test
    public void notNullWithNonNullArgumentWillNotDoAnything() {

        // deutsch fixtures
        final Object input = new Object();
        final String message = null;

        // when
        Utils.notNull(input, message);
    }

    @Test
    public void notNullWithNullArgumentAndMessageWillThrowExceptionWithGivenMessage() {

        // deutsch fixtures
        final Object input = null;
        final String message = "Tut tut, can't give null";

        // when
        try {
            Utils.notNull(input, message);
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage()).isEqualTo(message);
        }
    }
}