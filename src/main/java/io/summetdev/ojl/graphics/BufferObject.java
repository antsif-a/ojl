package io.summetdev.ojl.graphics;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.*;
import static org.lwjgl.system.MemoryUtil.*;

public abstract class BufferObject extends GLStruct {
    private final int type;

    protected BufferObject(int id, int type) {
        super(id);
        this.type = type;
    }

    public void bind() {
        glBindBuffer(type, id);
    }

    public void data(short[] data, VertexBufferObject.DrawType draw) {
        glBufferData(type, data, draw.code);
    }

    public void data(int[] data, VertexBufferObject.DrawType draw) {
        glBufferData(type, data, draw.code);
    }

    public void data(long[] data, VertexBufferObject.DrawType draw) {
        glBufferData(type, data, draw.code);
    }

    public void data(float[] data, VertexBufferObject.DrawType draw) {
        glBufferData(type, data, draw.code);
    }

    public void data(double[] data, VertexBufferObject.DrawType draw) {
        glBufferData(type, data, draw.code);
    }

    public void vertexAttrib(int index, int size, DataType dataType, boolean normalized, int stride, long pointer) {
        glVertexAttribPointer(index, size, dataType.code, normalized, stride, pointer);
    }

    public void vertexAttrib(int index, int size, DataType dataType, boolean normalized, int stride) {
        glVertexAttribPointer(index, size, dataType.code, normalized, stride, NULL);
    }

    public void vertexAttrib(int index, int size, DataType dataType, boolean normalized) {
        glVertexAttribPointer(index, size, dataType.code, normalized, 0, NULL);
    }

    public void enableVertexAttrib(int index) {
        glEnableVertexAttribArray(index);
    }

    public void delete() {
        glDeleteBuffers(id);
    }

    public enum BufferType {
        ArrayBuffer(GL_ARRAY_BUFFER),
        ElementArrayBuffer(GL_ELEMENT_ARRAY_BUFFER);

        public final int code;

        BufferType(int code) {
            this.code = code;
        }
    }

    public enum DrawType {
        StreamDraw(GL_STREAM_DRAW),
        StaticDraw(GL_STATIC_DRAW),
        DynamicDraw(GL_DYNAMIC_DRAW);

        public final int code;

        DrawType(int code) {
            this.code = code;
        }
    }

    public enum DataType {
        Byte(GL_BYTE),
        UnsignedByte(GL_UNSIGNED_BYTE),
        Short(GL_SHORT),
        UnsignedShort(GL_UNSIGNED_SHORT),
        Int(GL_INT),
        UnsignedInt(GL_UNSIGNED_INT),
        Float(GL_FLOAT),
        Double(GL_DOUBLE);

        public final int code;

        DataType(int code) {
            this.code = code;
        }
    }

    public enum BeginMode {
        Points(GL_POINTS),
        Lines(GL_LINES),
        LineLoop(GL_LINE_LOOP),
        LineStrip(GL_LINE_STRIP),
        Triangles(GL_TRIANGLES),
        TriangleStrip(GL_TRIANGLE_STRIP),
        TriangleFan(GL_TRIANGLE_FAN),
        Quads(GL_QUADS);

        public final int code;

        BeginMode(int code) {
            this.code = code;
        }
    }
}
