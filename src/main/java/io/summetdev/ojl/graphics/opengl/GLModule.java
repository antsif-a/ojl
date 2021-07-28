package io.summetdev.ojl.graphics.opengl;

import dagger.Module;
import dagger.*;
import io.summetdev.ojl.graphics.opengl.impl.*;

@Module
public abstract class GLModule {
    @Binds
    abstract GL20 provideGL20(GL20Impl impl);

    @Binds
    abstract GL30 provideGL30(GL30Impl impl);
}
