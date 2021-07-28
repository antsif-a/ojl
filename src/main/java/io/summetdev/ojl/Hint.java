package io.summetdev.ojl;

import static org.lwjgl.glfw.GLFW.*;

public enum Hint {
    // region window related hints
    Focused(GLFW_FOCUSED),
    Iconified(GLFW_ICONIFIED),
    Resizable(GLFW_RESIZABLE),
    Visible(GLFW_VISIBLE),
    Decorated(GLFW_DECORATED),
    AutoIconify(GLFW_AUTO_ICONIFY),
    Floating(GLFW_FLOATING),
    Maximized(GLFW_MAXIMIZED),
    CenterCursor(GLFW_CENTER_CURSOR),
    TransparentFrameBuffer(GLFW_TRANSPARENT_FRAMEBUFFER),
    Hovered(GLFW_HOVERED),
    FocusOnShow(GLFW_FOCUS_ON_SHOW),
    // endregion

    // region framebuffer related hints
    RedBits(GLFW_RED_BITS),
    GreenBits(GLFW_GREEN_BITS),
    BlueBits(GLFW_BLUE_BITS),
    DepthBits(GLFW_DEPTH_BITS),
    StencilBits(GLFW_STENCIL_BITS),
    Stereo(GLFW_STEREO),
    Samples(GLFW_SAMPLES),
    SRGBCapable(GLFW_SRGB_CAPABLE),
    DoubleBuffer(GLFW_DOUBLEBUFFER),
    // endregion

    // region monitor related hints
    RefreshRate(GLFW_REFRESH_RATE),
    // endregion

    // region context related hints
    ClientAPI(GLFW_CLIENT_API),
    ContextCreationAPI(GLFW_CONTEXT_CREATION_API),
    VersionMajor(GLFW_CONTEXT_VERSION_MAJOR),
    VersionMinor(GLFW_CONTEXT_VERSION_MINOR),
    Revision(GLFW_CONTEXT_REVISION),
    ForwardCompatible(GLFW_OPENGL_FORWARD_COMPAT),
    Debug(GLFW_OPENGL_DEBUG_CONTEXT),
    ReleaseBehavior(GLFW_CONTEXT_RELEASE_BEHAVIOR),
    NoError(GLFW_CONTEXT_NO_ERROR),
    Robustness(GLFW_CONTEXT_ROBUSTNESS),
    // endregion

    // region macos specific window hints
    CocoaRetinaFramebuffer(GLFW_COCOA_RETINA_FRAMEBUFFER),
    CocoaFrameName(GLFW_COCOA_FRAME_NAME),
    CocoaGraphicsSwitching(GLFW_COCOA_GRAPHICS_SWITCHING),
    // endregion

    // region X11 specific window hints
    X11ClassName(GLFW_X11_CLASS_NAME),
    X11InstanceName(GLFW_X11_INSTANCE_NAME),
    // endregion
    ;

    public int code;

    Hint(int code) {
        this.code = code;
    }
}
