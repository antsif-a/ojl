package io.summetdev.ojl.annotation;

import io.summetdev.ojl.annotation.internal.*;

import javax.inject.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

public class ApplicationConfigProcessor extends BaseProcessor {
    private static final Class<io.summetdev.ojl.boot.Config> configType = io.summetdev.ojl.boot.Config.class;

    @Inject
    public ApplicationConfigProcessor(Class<?> processedClass) {
        super(processedClass);
    }

    public Field getConfig() {
        List<Field> annotatedFields = findFieldsAnnotated(ApplicationConfig.class).stream()
            .filter(f -> f.getType().isAssignableFrom(configType))
            .collect(Collectors.toList());

        if (annotatedFields.size() > 1) {
            throw new IllegalStateException("Only one field should be marked as config");
        }

        if (annotatedFields.isEmpty()) {
            throw new IllegalStateException("Not found fields marked as config");
        }

        return annotatedFields.get(0);
    }
}
