package io.summetdev.ojl.window;

import dagger.Module;
import dagger.*;
import io.summetdev.ojl.*;

@Module
public abstract class WindowModule {
    @Binds
    abstract Monitor provideMonitor(MonitorImpl impl);

    @Binds
    abstract Window provideWindow(WindowImpl impl);
}
