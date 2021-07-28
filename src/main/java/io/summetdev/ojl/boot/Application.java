package io.summetdev.ojl.boot;

import io.summetdev.ojl.annotation.*;
import io.summetdev.ojl.annotation.internal.*;
import io.summetdev.ojl.graphics.glfw.*;

public abstract class Application implements LifecycleListener {
    protected Application() {
        ApplicationComponent appComponent = DaggerApplicationComponent.create();
        Config config = getConfig();

        Context context = appComponent.context();
        context.init();

        Window window = context.createWindow(config.width(), config.height(), config.title());
        window.makeContextCurrent();

        init();
        while (!window.shouldClose()) {
            update();
            window.swapBuffers();
            context.pollEvents();
        }
    }

    public static <T> void run(Class<T> mainClass) {
        T instance = Reflect.constructor(mainClass).get();

        ApplicationConfigProcessor configProcessor = new ApplicationConfigProcessor(mainClass);
        Config config = Reflect.get(instance, configProcessor.getConfig());

        if (instance instanceof LifecycleListener m) {
            run(m::init, m::update, config);
        } else {
            run(() -> {
            }, () -> {
            }, config);
        }
    }

    public static void run(Runnable init, Runnable update, Config config) {
        new Application() {
            @Override
            public void init() {
                init.run();
            }

            @Override
            public void update() {
                update.run();
            }

            @Override
            protected Config getConfig() {
                return config;
            }
        };
    }

    public abstract void init();

    public abstract void update();

    protected Config getConfig() {
        return new ConfigBuilder().build();
    }
}
