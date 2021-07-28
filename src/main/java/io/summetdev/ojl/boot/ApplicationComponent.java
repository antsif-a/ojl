package io.summetdev.ojl.boot;

import dagger.*;
import io.summetdev.ojl.graphics.glfw.*;

import javax.inject.*;

@Component
@Singleton
public interface ApplicationComponent {
    Context context();
}
