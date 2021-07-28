package io.summetdev.ojl.test;

import io.summetdev.ojl.boot.*;
import org.lwjgl.opengl.*;

public class ConfigTest extends Application {

    public static void main(String[] args) {
        new ConfigTest();
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

    @Override
    protected Config getConfig() {
        return new ConfigBuilder()
                .title("Config Test").width(700).height(500)
                .build();
    }
}
