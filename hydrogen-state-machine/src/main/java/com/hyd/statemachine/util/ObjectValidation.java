package com.hyd.statemachine.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Objects;

public class ObjectValidation {

    public static void nonNull(String message, Object... objects) {
        Objects.requireNonNull(objects, message);

        if (objects.length == 0) {
            throw new NullPointerException(message);
        }

        if (objects.length == 1) {
            Object object = objects[0];
            Objects.requireNonNull(object, message);

            if (object.getClass().isArray()) {
                int length = Array.getLength(object);
                for (int i = 0; i < length; i++) {
                    nonNull(message, Array.get(object, i));
                }

            } else if (object instanceof Collection) {
                Collection<?> collection = (Collection<?>) object;
                for (Object o : collection) {
                    nonNull(message, o);
                }
            }

        } else {
            for (Object object : objects) {
                nonNull(message, object);
            }
        }

    }
}
