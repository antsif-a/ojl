package io.summetdev.ojl.graphics.glfw;

import dagger.assisted.*;
import io.summetdev.ojl.graphics.glfw.impl.*;

public interface Monitor {
    long getId();

    // region monitor state getters

    String getName();

    int getX();

    int getY();

    int getPhysicalWidth();

    int getPhysicalHeight();

    float getContentScaleX();

    float getContentScaleY();

    int getWorkareaX();

    int getWorkareaY();

    int getWorkareaWidth();

    int getWorkareaHeight();

    VideoMode getVideoMode();

    // endregion

    @AssistedFactory
    interface Factory {
        MonitorImpl create(long address);
    }
}
