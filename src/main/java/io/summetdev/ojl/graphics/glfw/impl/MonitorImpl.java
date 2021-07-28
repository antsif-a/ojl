package io.summetdev.ojl.graphics.glfw.impl;

import dagger.assisted.*;
import io.summetdev.ojl.graphics.glfw.*;
import org.lwjgl.system.*;

import static org.lwjgl.glfw.GLFW.*;

@NativeType("struct GLFWmonitor")
public class MonitorImpl extends Struct implements Monitor {
    private static final int[] tmpi = new int[1];
    private static final float[] tmpf = new float[1];

    @AssistedInject
    public MonitorImpl(@Assisted long address) {
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

    // region monitor state getters

    @Override
    public String getName() {
        return glfwGetMonitorName(address);
    }

    @Override
    public int getX() {
        glfwGetMonitorPos(address, tmpi, null);
        return tmpi[0];
    }

    @Override
    public int getY() {
        glfwGetMonitorPos(address, null, tmpi);
        return tmpi[0];
    }

    @Override
    public int getPhysicalWidth() {
        glfwGetMonitorPhysicalSize(address, tmpi, null);
        return tmpi[0];
    }

    @Override
    public int getPhysicalHeight() {
        glfwGetMonitorPhysicalSize(address, null, tmpi);
        return tmpi[0];
    }

    @Override
    public float getContentScaleX() {
        glfwGetMonitorContentScale(address, tmpf, null);
        return tmpf[0];
    }

    @Override
    public float getContentScaleY() {
        glfwGetMonitorContentScale(address, null, tmpf);
        return tmpf[0];
    }

    @Override
    public int getWorkareaX() {
        glfwGetMonitorWorkarea(address, tmpi, null, null, null);
        return tmpi[0];
    }

    @Override
    public int getWorkareaY() {
        glfwGetMonitorWorkarea(address, null, tmpi, null, null);
        return tmpi[0];
    }

    @Override
    public int getWorkareaWidth() {
        glfwGetMonitorWorkarea(address, null, null, tmpi, null);
        return tmpi[0];
    }

    @Override
    public int getWorkareaHeight() {
        glfwGetMonitorWorkarea(address, null, null, null, tmpi);
        return tmpi[0];
    }

    @Override
    public VideoMode getVideoMode() {
        return (VideoMode) glfwGetVideoMode(address);
    }

    // endregion
}
