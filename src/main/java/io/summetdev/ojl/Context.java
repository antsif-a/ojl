package io.summetdev.ojl;

import dagger.*;
import dagger.Module;
import io.summetdev.ojl.window.*;
import org.lwjgl.*;

import javax.inject.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

@Singleton
public final class Context {
    private boolean isInitialized = false;

    @Inject
    public Context() {}

    public void init() {
        if (!glfwInit()) {
            throw new IllegalStateException("Failed to init GLFW");
        }

        isInitialized = true;
    }

    public void terminate() {
        glfwTerminate();
    }

    public void initHint(Hint hint, int value) {
        glfwInitHint(hint.code, value);
    }

    public void initHint(Hint hint, boolean value) {
        glfwInitHint(hint.code, value ? GLFW_TRUE : GLFW_FALSE);
    }

    public void windowHint(Hint hint, int value) {
        glfwWindowHint(hint.code, value);
    }

    public void windowHint(Hint hint, boolean value) {
        glfwWindowHint(hint.code, value ? GLFW_TRUE : GLFW_FALSE);
    }

    public void windowHint(Hint hint, String value) {
        glfwWindowHintString(hint.code, value);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void waitEvents() {
        glfwWaitEvents();
    }

    public void waitEventsTimeout(double timeout) {
        glfwWaitEventsTimeout(timeout);
    }

    public void swapInterval(int interval) {
        glfwSwapInterval(interval);
    }

    public void setErrorCallback(ErrorCallback callback) {
        glfwSetErrorCallback((error, description) -> callback.invoke(error, memUTF8(description)));
    }

    public Monitor[] getMonitors() {
        PointerBuffer buffer = glfwGetMonitors();
        if (buffer == null) {
            return new MonitorImpl[0];
        }

        Monitor[] monitors = new Monitor[buffer.capacity()];

        for (int i = 0; i < monitors.length; i++) {
            monitors[i] = new MonitorImpl(buffer.get());
        }

        return monitors;
    }

    public Monitor getPrimaryMonitor() {
        return new MonitorImpl(glfwGetPrimaryMonitor());
    }

    // region utility methods

    public Window createWindow(int width, int height, CharSequence title) {
        return createWindow(width, height, title, null, null);
    }

    public Window createWindow(int width, int height, CharSequence title, Monitor monitor) {
        return createWindow(width, height, title, monitor, null);
    }

    public Window createWindow(int width, int height, CharSequence title, Monitor monitor, Window share) {
        if (!isInitialized) {
            throw new IllegalStateException("Call Context#init first");
        }

        long address = glfwCreateWindow(
                width, height, title,
                monitor == null ? NULL : monitor.getId(),
                share == null ? NULL : share.getId()
        );

        if (address == NULL) {
            throw new IllegalStateException("Failed to create window");
        }

        return new WindowImpl(address);
    }

    @Module
    public static class ContextModule {

        @Provides @Singleton
        public Context provideContext() {
            return new Context();
        }
    }
}
