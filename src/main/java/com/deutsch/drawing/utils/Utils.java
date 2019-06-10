package com.deutsch.drawing.utils;

public class Utils {

    public static void notNull(Object object, String message) {

        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
