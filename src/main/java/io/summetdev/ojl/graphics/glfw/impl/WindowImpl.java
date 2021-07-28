package io.summetdev.ojl.graphics.glfw.impl;

import dagger.assisted.*;
import io.summetdev.ojl.graphics.glfw.*;
import io.summetdev.ojl.input.*;
import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.*;

@NativeType("struct GLFWwindow")
public class WindowImpl extends Struct implements Window {
    private static final int[] tmpi = new int[1];

    @AssistedInject
    public WindowImpl(@Assisted long address) {
        super(address, null);
    }

    @Override
    public int sizeof() {
        // no data fields
        return 0;
    }

    @Override
    public long getId() {
        return address;
    }

    // region window control

    @Override
    public void show() {
        glfwShowWindow(address);
    }

    @Override
    public void hide() {
        glfwHideWindow(address);
    }

    @Override
    public void destroy() {
        glfwDestroyWindow(address);
    }

    @Override
    public void focus() {
        glfwFocusWindow(address);
    }

    @Override
    public void iconify() {
        glfwIconifyWindow(address);
    }

    @Override
    public void maximize() {
        glfwMaximizeWindow(address);
    }

    @Override
    public void restore() {
        glfwRestoreWindow(address);
    }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(address);
    }

    @Override
    public void makeContextCurrent() {
        glfwMakeContextCurrent(address);
        createCapabilities();
    }

    // endregion
    // region window state getters

    @Override
    public int getWidth() {
        glfwGetWindowSize(address, tmpi, null);
        return tmpi[0];
    }

    @Override
    public int getHeight() {
        glfwGetWindowSize(address, null, tmpi);
        return tmpi[0];
    }

    @Override
    public int getX() {
        glfwGetWindowPos(address, tmpi, null);
        return tmpi[0];
    }

    @Override
    public int getY() {
        glfwGetWindowPos(address, null, tmpi);
        return tmpi[0];
    }

    @Override
    public MonitorImpl getMonitor() {
        return new MonitorImpl(glfwGetWindowMonitor(address));
    }

    @Override
    public float getOpacity() {
        return glfwGetWindowOpacity(address);
    }

    @Override
    public void setOpacity(float opacity) {
        glfwSetWindowOpacity(address, opacity);
    }

    @Override
    public int getAttribute(Hint attribute) {
        return glfwGetWindowAttrib(address, attribute.code);
    }

    // endregion
    // region window state setters

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(address);
    }

    @Override
    public void setTitle(String title) {
        glfwSetWindowTitle(address, title);
    }

    @Override
    public void setSize(int width, int height) {
        glfwSetWindowSize(address, width, height);
    }

    @Override
    public void setPosition(int x, int y) {
        glfwSetWindowPos(address, x, y);
    }

    @Override
    public void setMonitor(Monitor monitor, int x, int y, int width, int height, int refreshRate) {
        glfwSetWindowMonitor(address, monitor.getId(), x, y, width, height, refreshRate);
    }

    @Override
    public void setIcon(int width, int height, ByteBuffer pixels) {
        GLFWImage image = GLFWImage.malloc().set(width, height, pixels);
        GLFWImage.Buffer imageBuffer = GLFWImage.malloc(1).put(0, image);
        glfwSetWindowIcon(address, imageBuffer);
    }

    @Override
    public void setAttribute(Hint attribute, int value) {
        glfwSetWindowAttrib(address, attribute.code, value);
    }

    @Override
    public void setShouldClose(boolean value) {
        glfwSetWindowShouldClose(address, value);
    }

    // endregion

    @Override
    public void setEventProcessor(EventProcessor processor) {
        glfwSetKeyCallback(address, (window, key, scancode, action, mods) -> {
            if (action == GLFW_RELEASE) {
                processor.onKeyReleased(this, Key.byCode(key));
            } else {
                processor.onKeyPressed(this, Key.byCode(key));
            }
        });
    }
}
