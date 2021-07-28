package io.summetdev.ojl.input;

import io.summetdev.ojl.graphics.glfw.*;

public interface EventProcessor {
    /**
     * Called when user presses a key.
     *
     * @param window window where this event occurred
     * @param key    key that was pressed
     */
    void onKeyPressed(Window window, Key key);

    /**
     * Called when user releases a key.
     *
     * @param window window where this event occurred
     * @param key    key that was released
     */
    void onKeyReleased(Window window, Key key);
}
