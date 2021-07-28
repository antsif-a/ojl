package io.summetdev.ojl.test;

import io.summetdev.ojl.*;
import io.summetdev.ojl.annotation.*;
import io.summetdev.ojl.boot.*;
import org.lwjgl.opengl.*;

import javax.inject.*;

public class ConfigAnnotationTest implements LifecycleListener {
    @ApplicationConfig
    Config config = new ConfigBuilder()
            .title("Config Annotation Test").width(700).height(500)
            .build();

    private final Window mainWindow;

    @Inject
    public ConfigAnnotationTest(Window mainWindow) {
        this.mainWindow = mainWindow;
    }

    public static void main(String[] args) {
        Application.run(ConfigAnnotationTest.class);
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
