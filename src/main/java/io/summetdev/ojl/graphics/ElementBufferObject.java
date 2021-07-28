package io.summetdev.ojl.graphics;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.system.MemoryUtil.*;

public class ElementBufferObject extends BufferObject {
    public ElementBufferObject(int id) {
        super(id, BufferType.ElementArrayBuffer.code);
    }

    public static ElementBufferObject create() {
        return new ElementBufferObject(glGenBuffers());
    }

    public void drawElements(BeginMode mode, int count, DataType indicesType, long offset) {
        glDrawElements(mode.code, count, indicesType.code, offset);
    }

    public void drawElements(BeginMode mode, int count, DataType indicesType) {
        glDrawElements(mode.code, count, indicesType.code, NULL);
    }
}
