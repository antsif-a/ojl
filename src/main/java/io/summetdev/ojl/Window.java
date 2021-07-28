package io.summetdev.ojl;

import io.summetdev.ojl.input.*;

import java.nio.*;

public interface Window {
    /**
     * @return unique id of this window, usually pointer to <code>GLFWwindow</code>
     */
    long getId();

    // region window control

    /**
     *
     */
    void show();

    void hide();

    void destroy();

    void focus();

    void iconify();

    void maximize();

    void restore();

    void swapBuffers();

    void makeContextCurrent();

    // endregion
    // region window state getters

    int getWidth();

    int getHeight();

    int getX();

    int getY();

    Monitor getMonitor();

    float getOpacity();

    void setOpacity(float opacity);

    int getAttribute(Hint attribute);

    // endregion
    // region window state setters

    boolean shouldClose();

    void setTitle(String title);

    void setSize(int width, int height);

    void setPosition(int x, int y);

    void setMonitor(Monitor monitor, int x, int y, int width, int height, int refreshRate);

    void setIcon(int width, int height, ByteBuffer pixels);

    void setAttribute(Hint attribute, int value);

    void setShouldClose(boolean shouldClose);

    // endregion

    void setEventProcessor(EventProcessor processor);
}
