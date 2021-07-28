package io.summetdev.ojl.test;

import io.summetdev.ojl.*;
import io.summetdev.ojl.graphics.*;
import io.summetdev.ojl.input.*;
import io.summetdev.ojl.graphics.BufferObject.*;
import org.junit.*;

import java.io.*;

import static org.lwjgl.opengl.GL33C.*;

public class ApplicationTest {
    private final float[] vertices = new float[]{
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
    };

    private Context context;
    private Window window;

    private final File shadersDir = new File("src/test/resources/shaders");
    private Shader shader;
    private VertexArrayObject vertexArray;
    private VertexBufferObject vertexBuffer;

    @Test
    public void start() {
        context = new Context();
        context.setErrorCallback((error, description) -> {
            throw new RuntimeException(description);
        });

        context.init();

        context.windowHint(Hint.Samples, 4);
        context.windowHint(Hint.VersionMajor, 3);
        context.windowHint(Hint.VersionMinor, 3);
        context.windowHint(Hint.ForwardCompatible, true);

        window = context.createWindow(700, 500, "Window");
        window.makeContextCurrent();
        window.setEventProcessor(new EventProcessorAdapter() {
            @Override
            public void onKeyPressed(Window window, Key key) {
                System.out.println("Key pressed: " + key.toString());
            }

            @Override
            public void onKeyReleased(Window window, Key key) {
                System.out.println("Key released: " + key.toString());
            }
        });

        init();

        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            render();

            window.swapBuffers();
            context.pollEvents();
        }

        terminate();
    }

    public void init() {
        // enable vsync
        context.swapInterval(1);

        vertexArray = VertexArrayObject.create();

        vertexBuffer = VertexBufferObject.create();
        vertexBuffer.bind();
        vertexBuffer.data(vertices, DrawType.StaticDraw);

        shader = Shader.create(new File(shadersDir, "color-vert.glsl"), new File(shadersDir, "color-frag.glsl"));
    }

    public void render() {
        shader.validate();
        shader.use();

        vertexArray.bind();

        vertexBuffer.bind();
        vertexBuffer.vertexAttrib(0, 3, DataType.Float, false);
        vertexBuffer.enableVertexAttrib(0);

        vertexBuffer.drawArrays(BeginMode.Triangles, 0, 3);
    }

    public void terminate() {
        shader.delete();
        vertexArray.delete();
        vertexBuffer.delete();

        context.terminate();
    }
}
