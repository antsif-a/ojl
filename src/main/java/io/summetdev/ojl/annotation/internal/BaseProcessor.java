package io.summetdev.ojl.annotation.internal;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class BaseProcessor {
    private final Class<?> processedClass;

    public BaseProcessor(Class<?> processedClass) {
        this.processedClass = processedClass;
    }

    public List<Field> findFieldsAnnotated(Class<? extends Annotation> ann) {
        List<Field> list = new ArrayList<>();
        Class<?> c = processedClass;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    list.add(field);
                }
            }
            c = c.getSuperclass();
        }

        return list;
    }
}
