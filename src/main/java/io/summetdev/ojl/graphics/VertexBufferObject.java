package io.summetdev.ojl.graphics;

import static org.lwjgl.opengl.GL33C.*;

public class VertexBufferObject extends BufferObject {
    public static VertexBufferObject create() {
        return new VertexBufferObject(glGenBuffers());
    }

    public VertexBufferObject(int id) {
        super(id, BufferType.ArrayBuffer.code);
    }

    public void drawArrays(BeginMode mode, int first, int count) {
        glDrawArrays(mode.code, first, count);
    }
}
