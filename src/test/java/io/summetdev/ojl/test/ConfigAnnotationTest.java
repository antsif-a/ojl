package io.summetdev.ojl.test;

import io.summetdev.ojl.annotation.*;
import io.summetdev.ojl.boot.*;
import org.junit.*;
import org.lwjgl.opengl.*;

public class ConfigAnnotationTest implements LifecycleListener {
    @ApplicationConfig
    Config config = new ConfigBuilder()
        .title("Config Annotation Test").width(700).height(500)
        .build();

    @Test
    public void main() {
        Application.run(getClass());
    }

    @Override
    public void init() {
        System.out.println("Init");
    }

    @Override
    public void update() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(1, 0, 0, 1);
    }
}
