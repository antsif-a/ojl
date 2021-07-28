package io.summetdev.ojl.graphics;

import static org.lwjgl.opengl.GL33C.*;

public class VertexArrayObject extends GLStruct {
    public static VertexArrayObject create() {
        return new VertexArrayObject(glGenVertexArrays());
    }

    public VertexArrayObject(int id) {
        super(id);
    }

    public void bind() {
        glBindVertexArray(id);
    }

    public void delete() {
        glDeleteVertexArrays(id);
    }
}
