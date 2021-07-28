package io.summetdev.ojl.annotation.internal;

import org.immutables.value.*;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Value.Style(
        visibility = Value.Style.ImplementationVisibility.PRIVATE,
        typeImmutable = "_*",
        typeBuilder = "*Builder",
        allowedClasspathAnnotations = {Generated.class},
        defaults = @Value.Immutable(prehash = true))
public @interface Builder {
}
