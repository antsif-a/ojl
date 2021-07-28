package io.summetdev.ojl.test;

import dagger.*;
import io.summetdev.ojl.graphics.gl.*;
import io.summetdev.ojl.graphics.glfw.*;
import org.junit.*;

import javax.inject.*;

import static io.summetdev.ojl.graphics.gl.GL20.*;

@Singleton
@Component(modules = {GLModule.class, Context.ContextModule.class})
interface TestComponent {
    void inject(GLDependencyTest test);
}

public class GLDependencyTest {

    @Inject
    public GL30 gl;

    @Inject
    public Context context;

    @Before
    public void setup() {
        DaggerTestComponent.create()
            .inject(this);
    }

    @Test
    public void start() {
        context.init();
        Window window = context.createWindow(700, 500, "GL Dependency Test");
        window.makeContextCurrent();
        context.swapInterval(1);

        while (!window.shouldClose()) {
            gl.glClear(GL_COLOR_BUFFER_BIT);
            gl.glClearColor(1, 0, 0, 1);

            window.swapBuffers();
            context.pollEvents();
        }
    }
}
