package io.summetdev.ojl.boot;

import dagger.*;
import io.summetdev.ojl.*;

import javax.inject.*;

@Component
@Singleton
public interface ApplicationComponent {
    Context context();
}
