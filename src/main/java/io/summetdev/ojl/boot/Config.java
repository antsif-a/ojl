package io.summetdev.ojl.boot;

import io.summetdev.ojl.annotation.internal.*;
import org.immutables.value.*;

@Value.Immutable
@Builder
public interface Config {
    /**
     * @return title of the window
     */
    @Value.Default
    default String title() {
        return "Application";
    }

    /**
     * @return width of the window
     */
    @Value.Default
    default int width() {
        return 700;
    }

    /**
     * @return height of the window
     */
    @Value.Default
    default int height() {
        return 500;
    }
}
