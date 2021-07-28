package io.summetdev.ojl.graphics.opengl.impl;

import org.lwjgl.opengl.*;

import javax.inject.*;
import java.nio.*;

public class GL20Impl implements io.summetdev.ojl.graphics.opengl.GL20 {
    private static ByteBuffer buffer = null;
    private static FloatBuffer floatBuffer = null;
    private static IntBuffer intBuffer = null;

    @Inject
    public GL20Impl() {}

    private static void ensureBufferCapacity(int numBytes) {
        if (buffer == null || buffer.capacity() < numBytes) {
            buffer = ByteBuffer.allocate(numBytes);
            floatBuffer = buffer.asFloatBuffer();
            intBuffer = buffer.asIntBuffer();
        }
    }

    private static FloatBuffer toFloatBuffer(float[] v, int offset, int count) {
        ensureBufferCapacity(count << 2);
        return floatBuffer.clear()
                .limit(count)
                .put(v, offset, count)
                .position(0);
    }

    private static IntBuffer toIntBuffer(int[] v, int offset, int count) {
        ensureBufferCapacity(count << 2);
        return intBuffer.clear()
                .limit(count)
                .put(v, offset, count)
                .position(0);
    }

    @Override
    public void glActiveTexture(int texture) {
        GL13.glActiveTexture(texture);
    }

    @Override
    public void glAttachShader(int program, int shader) {
        GL20.glAttachShader(program, shader);
    }

    @Override
    public void glBindAttribLocation(int program, int index, String name) {
        GL20.glBindAttribLocation(program, index, name);
    }

    @Override
    public void glBindBuffer(int target, int buffer) {
        GL15.glBindBuffer(target, buffer);
    }

    @Override
    public void glBindFramebuffer(int target, int framebuffer) {
        EXTFramebufferObject.glBindFramebufferEXT(target, framebuffer);
    }

    @Override
    public void glBindRenderbuffer(int target, int renderbuffer) {
        EXTFramebufferObject.glBindRenderbufferEXT(target, renderbuffer);
    }

    @Override
    public void glBindTexture(int target, int texture) {
        GL11.glBindTexture(target, texture);
    }

    @Override
    public void glBlendColor(float red, float green, float blue, float alpha) {
        GL14.glBlendColor(red, green, blue, alpha);
    }

    @Override
    public void glBlendEquation(int mode) {
        GL14.glBlendEquation(mode);
    }

    @Override
    public void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
        GL20.glBlendEquationSeparate(modeRGB, modeAlpha);
    }

    @Override
    public void glBlendFunc(int sfactor, int dfactor) {
        GL11.glBlendFunc(sfactor, dfactor);
    }

    @Override
    public void glBlendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        GL14.glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
    }

    @Override
    public void glBufferData(int target, int size, Buffer data, int usage) {
        if (data == null)
            GL15.glBufferData(target, size, usage);
        else if (data instanceof ByteBuffer)
            GL15.glBufferData(target, (ByteBuffer) data, usage);
        else if (data instanceof IntBuffer)
            GL15.glBufferData(target, (IntBuffer) data, usage);
        else if (data instanceof FloatBuffer)
            GL15.glBufferData(target, (FloatBuffer) data, usage);
        else if (data instanceof DoubleBuffer)
            GL15.glBufferData(target, (DoubleBuffer) data, usage);
        else if (data instanceof ShortBuffer)
            GL15.glBufferData(target, (ShortBuffer) data, usage);
    }

    @Override
    public void glBufferSubData(int target, int offset, int size, Buffer data) {
        if (data == null)
            throw new RuntimeException("Using null for the data not possible");
        else if (data instanceof ByteBuffer)
            GL15.glBufferSubData(target, offset, (ByteBuffer) data);
        else if (data instanceof IntBuffer)
            GL15.glBufferSubData(target, offset, (IntBuffer) data);
        else if (data instanceof FloatBuffer)
            GL15.glBufferSubData(target, offset, (FloatBuffer) data);
        else if (data instanceof DoubleBuffer)
            GL15.glBufferSubData(target, offset, (DoubleBuffer) data);
        else if (data instanceof ShortBuffer)
            GL15.glBufferSubData(target, offset, (ShortBuffer) data);
    }

    @Override
    public int glCheckFramebufferStatus(int target) {
        return EXTFramebufferObject.glCheckFramebufferStatusEXT(target);
    }

    @Override
    public void glClear(int mask) {
        GL11.glClear(mask);
    }

    @Override
    public void glClearColor(float red, float green, float blue, float alpha) {
        GL11.glClearColor(red, green, blue, alpha);
    }

    @Override
    public void glClearDepthf(float depth) {
        GL11.glClearDepth(depth);
    }

    @Override
    public void glClearStencil(int s) {
        GL11.glClearStencil(s);
    }

    @Override
    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        GL11.glColorMask(red, green, blue, alpha);
    }

    @Override
    public void glCompileShader(int shader) {
        GL20.glCompileShader(shader);
    }

    @Override
    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border,
                                       int imageSize, Buffer data) {
        if (data instanceof ByteBuffer) {
            GL13.glCompressedTexImage2D(target, level, internalformat, width, height, border, (ByteBuffer) data);
        } else {
            throw new RuntimeException("Can't use " + data.getClass().getName() + " with this method. Use ByteBuffer instead");
        }
    }

    @Override
    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format,
                                          int imageSize, Buffer data) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
        GL11.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
    }

    @Override
    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
    }

    @Override
    public int glCreateProgram() {
        return GL20.glCreateProgram();
    }

    @Override
    public int glCreateShader(int type) {
        return GL20.glCreateShader(type);
    }

    @Override
    public void glCullFace(int mode) {
        GL11.glCullFace(mode);
    }

    @Override
    public void glDeleteBuffers(IntBuffer buffers) {
        GL15.glDeleteBuffers(buffers);
    }

    @Override
    public void glDeleteBuffer(int buffer) {
        GL15.glDeleteBuffers(buffer);
    }

    @Override
    public void glDeleteFramebuffers(IntBuffer framebuffers) {
        EXTFramebufferObject.glDeleteFramebuffersEXT(framebuffers);
    }

    @Override
    public void glDeleteFramebuffer(int framebuffer) {
        EXTFramebufferObject.glDeleteFramebuffersEXT(framebuffer);
    }

    @Override
    public void glDeleteProgram(int program) {
        GL20.glDeleteProgram(program);
    }

    @Override
    public void glDeleteRenderbuffers(IntBuffer renderbuffers) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(renderbuffers);
    }

    @Override
    public void glDeleteRenderbuffer(int renderbuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(renderbuffer);
    }

    @Override
    public void glDeleteShader(int shader) {
        GL20.glDeleteShader(shader);
    }

    @Override
    public void glDeleteTextures(IntBuffer textures) {
        GL11.glDeleteTextures(textures);
    }

    @Override
    public void glDeleteTexture(int texture) {
        GL11.glDeleteTextures(texture);
    }

    @Override
    public void glDepthFunc(int func) {
        GL11.glDepthFunc(func);
    }

    @Override
    public void glDepthMask(boolean flag) {
        GL11.glDepthMask(flag);
    }

    @Override
    public void glDepthRangef(float zNear, float zFar) {
        GL11.glDepthRange(zNear, zFar);
    }

    @Override
    public void glDetachShader(int program, int shader) {
        GL20.glDetachShader(program, shader);
    }

    @Override
    public void glDisable(int cap) {
        GL11.glDisable(cap);
    }

    @Override
    public void glDisableVertexAttribArray(int index) {
        GL20.glDisableVertexAttribArray(index);
    }

    @Override
    public void glDrawArrays(int mode, int first, int count) {
        GL11.glDrawArrays(mode, first, count);
    }

    @Override
    public void glDrawElements(int mode, int count, int type, Buffer indices) {
        if (indices instanceof ShortBuffer sb && type == GL_UNSIGNED_SHORT)
            GL11.glDrawElements(mode, sb);
        else if (indices instanceof ByteBuffer bb && type == GL_UNSIGNED_SHORT)
            GL11.glDrawElements(mode, bb.asShortBuffer());
        else if (indices instanceof ByteBuffer bb && type == GL_UNSIGNED_BYTE)
            GL11.glDrawElements(mode, bb);
        else throw new RuntimeException("Can't use " + indices.getClass().getName()
                    + " with this method. Use ShortBuffer or ByteBuffer instead");
    }

    @Override
    public void glEnable(int cap) {
        GL11.glEnable(cap);
    }

    @Override
    public void glEnableVertexAttribArray(int index) {
        GL20.glEnableVertexAttribArray(index);
    }

    @Override
    public void glFinish() {
        GL11.glFinish();
    }

    @Override
    public void glFlush() {
        GL11.glFlush();
    }

    @Override
    public void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        EXTFramebufferObject.glFramebufferRenderbufferEXT(target, attachment, renderbuffertarget, renderbuffer);
    }

    @Override
    public void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
        EXTFramebufferObject.glFramebufferTexture2DEXT(target, attachment, textarget, texture, level);
    }

    @Override
    public void glFrontFace(int mode) {
        GL11.glFrontFace(mode);
    }

    @Override
    public void glGenBuffers(IntBuffer buffers) {
        GL15.glGenBuffers(buffers);
    }

    @Override
    public int glGenBuffer() {
        return GL15.glGenBuffers();
    }

    @Override
    public void glGenFramebuffers(IntBuffer framebuffers) {
        EXTFramebufferObject.glGenFramebuffersEXT(framebuffers);
    }

    @Override
    public int glGenFramebuffer() {
        return EXTFramebufferObject.glGenFramebuffersEXT();
    }

    @Override
    public void glGenRenderbuffers(IntBuffer renderbuffers) {
        EXTFramebufferObject.glGenRenderbuffersEXT(renderbuffers);
    }

    @Override
    public int glGenRenderbuffer() {
        return EXTFramebufferObject.glGenRenderbuffersEXT();
    }

    @Override
    public void glGenTextures(IntBuffer textures) {
        GL11.glGenTextures(textures);
    }

    @Override
    public int glGenTexture() {
        return GL11.glGenTextures();
    }

    @Override
    public void glGenerateMipmap(int target) {
        EXTFramebufferObject.glGenerateMipmapEXT(target);
    }

    @Override
    public String glGetActiveAttrib(int program, int index, IntBuffer size, IntBuffer type) {
        return GL20.glGetActiveAttrib(program, index, 256, size, type);
    }

    @Override
    public String glGetActiveUniform(int program, int index, IntBuffer size, IntBuffer type) {
        return GL20.glGetActiveUniform(program, index, 256, size, type);
    }

    @Override
    public void glGetAttachedShaders(int program, int maxcount, Buffer count, IntBuffer shaders) {
        GL20.glGetAttachedShaders(program, (IntBuffer) count, shaders);
    }

    @Override
    public int glGetAttribLocation(int program, String name) {
        return GL20.glGetAttribLocation(program, name);
    }

    @Override
    public void glGetBooleanv(int pname, Buffer params) {
        GL11.glGetBooleanv(pname, (ByteBuffer) params);
    }

    @Override
    public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
        GL15.glGetBufferParameteriv(target, pname, params);
    }

    @Override
    public int glGetError() {
        return GL11.glGetError();
    }

    @Override
    public void glGetFloatv(int pname, FloatBuffer params) {
        GL11.glGetFloatv(pname, params);
    }

    @Override
    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params) {
        EXTFramebufferObject.glGetFramebufferAttachmentParameterivEXT(target, attachment, pname, params);
    }

    @Override
    public void glGetIntegerv(int pname, IntBuffer params) {
        GL11.glGetIntegerv(pname, params);
    }

    @Override
    public String glGetProgramInfoLog(int program) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
        buffer.order(ByteOrder.nativeOrder());
        ByteBuffer tmp = ByteBuffer.allocateDirect(4);
        tmp.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = tmp.asIntBuffer();

        GL20.glGetProgramInfoLog(program, intBuffer, buffer);
        int numBytes = intBuffer.get(0);
        byte[] bytes = new byte[numBytes];
        buffer.get(bytes);
        return new String(bytes);
    }

    @Override
    public void glGetProgramiv(int program, int pname, IntBuffer params) {
        GL20.glGetProgramiv(program, pname, params);
    }

    @Override
    public void glGetRenderbufferParameteriv(int target, int pname, IntBuffer params) {
        EXTFramebufferObject.glGetRenderbufferParameterivEXT(target, pname, params);
    }

    @Override
    public String glGetShaderInfoLog(int shader) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
        buffer.order(ByteOrder.nativeOrder());
        ByteBuffer tmp = ByteBuffer.allocateDirect(4);
        tmp.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = tmp.asIntBuffer();

        GL20.glGetShaderInfoLog(shader, intBuffer, buffer);
        int numBytes = intBuffer.get(0);
        byte[] bytes = new byte[numBytes];
        buffer.get(bytes);
        return new String(bytes);
    }

    @Override
    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {
        throw new UnsupportedOperationException("unsupported, won't implement");
    }

    @Override
    public void glGetShaderiv(int shader, int pname, IntBuffer params) {
        GL20.glGetShaderiv(shader, pname, params);
    }

    @Override
    public String glGetString(int name) {
        return GL11.glGetString(name);
    }

    @Override
    public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
        GL11.glGetTexParameterfv(target, pname, params);
    }

    @Override
    public void glGetTexParameteriv(int target, int pname, IntBuffer params) {
        GL11.glGetTexParameteriv(target, pname, params);
    }

    @Override
    public int glGetUniformLocation(int program, String name) {
        return GL20.glGetUniformLocation(program, name);
    }

    @Override
    public void glGetUniformfv(int program, int location, FloatBuffer params) {
        GL20.glGetUniformfv(program, location, params);
    }

    @Override
    public void glGetUniformiv(int program, int location, IntBuffer params) {
        GL20.glGetUniformiv(program, location, params);
    }

    @Override
    public void glGetVertexAttribPointerv(int index, int pname, Buffer pointer) {
        throw new UnsupportedOperationException("Unsupported, won't implement");
    }

    @Override
    public void glGetVertexAttribfv(int index, int pname, FloatBuffer params) {
        GL20.glGetVertexAttribfv(index, pname, params);
    }

    @Override
    public void glGetVertexAttribiv(int index, int pname, IntBuffer params) {
        GL20.glGetVertexAttribiv(index, pname, params);
    }

    @Override
    public void glHint(int target, int mode) {
        GL11.glHint(target, mode);
    }

    @Override
    public boolean glIsBuffer(int buffer) {
        return GL15.glIsBuffer(buffer);
    }

    @Override
    public boolean glIsEnabled(int cap) {
        return GL11.glIsEnabled(cap);
    }

    @Override
    public boolean glIsFramebuffer(int framebuffer) {
        return EXTFramebufferObject.glIsFramebufferEXT(framebuffer);
    }

    @Override
    public boolean glIsProgram(int program) {
        return GL20.glIsProgram(program);
    }

    @Override
    public boolean glIsRenderbuffer(int renderbuffer) {
        return EXTFramebufferObject.glIsRenderbufferEXT(renderbuffer);
    }

    @Override
    public boolean glIsShader(int shader) {
        return GL20.glIsShader(shader);
    }

    @Override
    public boolean glIsTexture(int texture) {
        return GL11.glIsTexture(texture);
    }

    @Override
    public void glLineWidth(float width) {
        GL11.glLineWidth(width);
    }

    @Override
    public void glLinkProgram(int program) {
        GL20.glLinkProgram(program);
    }

    @Override
    public void glPixelStorei(int pname, int param) {
        GL11.glPixelStorei(pname, param);
    }

    @Override
    public void glPolygonOffset(float factor, float units) {
        GL11.glPolygonOffset(factor, units);
    }

    @Override
    public void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels) {
        if (pixels instanceof ByteBuffer)
            GL11.glReadPixels(x, y, width, height, format, type, (ByteBuffer) pixels);
        else if (pixels instanceof ShortBuffer)
            GL11.glReadPixels(x, y, width, height, format, type, (ShortBuffer) pixels);
        else if (pixels instanceof IntBuffer)
            GL11.glReadPixels(x, y, width, height, format, type, (IntBuffer) pixels);
        else if (pixels instanceof FloatBuffer)
            GL11.glReadPixels(x, y, width, height, format, type, (FloatBuffer) pixels);
        else
            throw new RuntimeException("Can't use " + pixels.getClass().getName()
                    + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer or FloatBuffer instead");
    }

    @Override
    public void glReleaseShaderCompiler() {
        // nothing to do here
    }

    @Override
    public void glRenderbufferStorage(int target, int internalformat, int width, int height) {
        EXTFramebufferObject.glRenderbufferStorageEXT(target, internalformat, width, height);
    }

    @Override
    public void glSampleCoverage(float value, boolean invert) {
        GL13.glSampleCoverage(value, invert);
    }

    @Override
    public void glShaderBinary(int n, IntBuffer shaders, int binaryformat, Buffer binary, int length) {
        throw new UnsupportedOperationException("Unsupported, won't implement");
    }

    @Override
    public void glShaderSource(int shader, String string) {
        GL20.glShaderSource(shader, string);
    }

    @Override
    public void glScissor(int x, int y, int width, int height) {
        GL11.glScissor(x, y, width, height);
    }

    @Override
    public void glStencilFunc(int func, int ref, int mask) {
        GL11.glStencilFunc(func, ref, mask);
    }

    @Override
    public void glStencilFuncSeparate(int face, int func, int ref, int mask) {
        GL20.glStencilFuncSeparate(face, func, ref, mask);
    }

    @Override
    public void glStencilMask(int mask) {
        GL11.glStencilMask(mask);
    }

    @Override
    public void glStencilMaskSeparate(int face, int mask) {
        GL20.glStencilMaskSeparate(face, mask);
    }

    @Override
    public void glStencilOp(int fail, int zfail, int zpass) {
        GL11.glStencilOp(fail, zfail, zpass);
    }

    @Override
    public void glStencilOpSeparate(int face, int fail, int zfail, int zpass) {
        GL20.glStencilOpSeparate(face, fail, zfail, zpass);
    }

    @Override
    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type,
                             Buffer pixels) {
        if (pixels == null)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, (ByteBuffer) null);
        else if (pixels instanceof ByteBuffer bb)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, bb);
        else if (pixels instanceof ShortBuffer sb)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, sb);
        else if (pixels instanceof IntBuffer ib)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, ib);
        else if (pixels instanceof FloatBuffer fb)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, fb);
        else if (pixels instanceof DoubleBuffer db)
            GL11.glTexImage2D(target, level, internalformat, width, height, border, format, type, db);
        else throw new RuntimeException("Can't use " + pixels.getClass().getName()
                    + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer, FloatBuffer or DoubleBuffer instead");
    }

    @Override
    public void glTexParameterf(int target, int pname, float param) {
        GL11.glTexParameterf(target, pname, param);
    }

    @Override
    public void glTexParameterfv(int target, int pname, FloatBuffer params) {
        GL11.glTexParameterfv(target, pname, params);
    }

    @Override
    public void glTexParameteri(int target, int pname, int param) {
        GL11.glTexParameteri(target, pname, param);
    }

    @Override
    public void glTexParameteriv(int target, int pname, IntBuffer params) {
        GL11.glTexParameteriv(target, pname, params);
    }

    @Override
    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type,
                                Buffer pixels) {
        if (pixels instanceof ByteBuffer bb)
            GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, bb);
        else if (pixels instanceof ShortBuffer sb)
            GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, sb);
        else if (pixels instanceof IntBuffer ib)
            GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, ib);
        else if (pixels instanceof FloatBuffer fb)
            GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, fb);
        else if (pixels instanceof DoubleBuffer db)
            GL11.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, db);
        else throw new RuntimeException("Can't use " + pixels.getClass().getName()
                    + " with this method. Use ByteBuffer, ShortBuffer, IntBuffer, FloatBuffer or DoubleBuffer instead");
    }

    @Override
    public void glUniform1f(int location, float x) {
        GL20.glUniform1f(location, x);
    }

    @Override
    public void glUniform1fv(int location, int count, FloatBuffer v) {
        GL20.glUniform1fv(location, v);
    }

    @Override
    public void glUniform1fv(int location, int count, float[] v, int offset) {
        GL20.glUniform1fv(location, toFloatBuffer(v, offset, count));
    }

    @Override
    public void glUniform1i(int location, int x) {
        GL20.glUniform1i(location, x);
    }

    @Override
    public void glUniform1iv(int location, int count, IntBuffer v) {
        GL20.glUniform1iv(location, v);
    }

    @Override
    public void glUniform1iv(int location, int count, int[] v, int offset) {
        GL20.glUniform1iv(location, toIntBuffer(v, offset, count));
    }

    @Override
    public void glUniform2f(int location, float x, float y) {
        GL20.glUniform2f(location, x, y);
    }

    @Override
    public void glUniform2fv(int location, int count, FloatBuffer v) {
        GL20.glUniform2fv(location, v);
    }

    @Override
    public void glUniform2fv(int location, int count, float[] v, int offset) {
        GL20.glUniform2fv(location, toFloatBuffer(v, offset, count << 1));
    }

    @Override
    public void glUniform2i(int location, int x, int y) {
        GL20.glUniform2i(location, x, y);
    }

    @Override
    public void glUniform2iv(int location, int count, IntBuffer v) {
        GL20.glUniform2iv(location, v);
    }

    @Override
    public void glUniform2iv(int location, int count, int[] v, int offset) {
        GL20.glUniform2iv(location, toIntBuffer(v, offset, count << 1));
    }

    @Override
    public void glUniform3f(int location, float x, float y, float z) {
        GL20.glUniform3f(location, x, y, z);
    }

    @Override
    public void glUniform3fv(int location, int count, FloatBuffer v) {
        GL20.glUniform3fv(location, v);
    }

    @Override
    public void glUniform3fv(int location, int count, float[] v, int offset) {
        GL20.glUniform3fv(location, toFloatBuffer(v, offset, count * 3));
    }

    @Override
    public void glUniform3i(int location, int x, int y, int z) {
        GL20.glUniform3i(location, x, y, z);
    }

    @Override
    public void glUniform3iv(int location, int count, IntBuffer v) {
        GL20.glUniform3iv(location, v);
    }

    @Override
    public void glUniform3iv(int location, int count, int[] v, int offset) {
        GL20.glUniform3iv(location, toIntBuffer(v, offset, count * 3));
    }

    @Override
    public void glUniform4f(int location, float x, float y, float z, float w) {
        GL20.glUniform4f(location, x, y, z, w);
    }

    @Override
    public void glUniform4fv(int location, int count, FloatBuffer v) {
        GL20.glUniform4fv(location, v);
    }

    @Override
    public void glUniform4fv(int location, int count, float[] v, int offset) {
        GL20.glUniform4fv(location, toFloatBuffer(v, offset, count << 2));
    }

    @Override
    public void glUniform4i(int location, int x, int y, int z, int w) {
        GL20.glUniform4i(location, x, y, z, w);
    }

    @Override
    public void glUniform4iv(int location, int count, IntBuffer v) {
        GL20.glUniform4iv(location, v);
    }

    @Override
    public void glUniform4iv(int location, int count, int[] v, int offset) {
        GL20.glUniform4iv(location, toIntBuffer(v, offset, count << 2));
    }

    @Override
    public void glUniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer value) {
        GL20.glUniformMatrix2fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix2fv(int location, int count, boolean transpose, float[] value, int offset) {
        GL20.glUniformMatrix2fv(location, transpose, toFloatBuffer(value, offset, count << 2));
    }

    @Override
    public void glUniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer value) {
        GL20.glUniformMatrix3fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix3fv(int location, int count, boolean transpose, float[] value, int offset) {
        GL20.glUniformMatrix3fv(location, transpose, toFloatBuffer(value, offset, count * 9));
    }

    @Override
    public void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value) {
        GL20.glUniformMatrix4fv(location, transpose, value);
    }

    @Override
    public void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset) {
        GL20.glUniformMatrix4fv(location, transpose, toFloatBuffer(value, offset, count << 4));
    }

    @Override
    public void glUseProgram(int program) {
        GL20.glUseProgram(program);
    }

    @Override
    public void glValidateProgram(int program) {
        GL20.glValidateProgram(program);
    }

    @Override
    public void glVertexAttrib1f(int indx, float x) {
        GL20.glVertexAttrib1f(indx, x);
    }

    @Override
    public void glVertexAttrib1fv(int indx, FloatBuffer values) {
        GL20.glVertexAttrib1f(indx, values.get());
    }

    @Override
    public void glVertexAttrib2f(int indx, float x, float y) {
        GL20.glVertexAttrib2f(indx, x, y);
    }

    @Override
    public void glVertexAttrib2fv(int indx, FloatBuffer values) {
        GL20.glVertexAttrib2f(indx, values.get(), values.get());
    }

    @Override
    public void glVertexAttrib3f(int indx, float x, float y, float z) {
        GL20.glVertexAttrib3f(indx, x, y, z);
    }

    @Override
    public void glVertexAttrib3fv(int indx, FloatBuffer values) {
        GL20.glVertexAttrib3f(indx, values.get(), values.get(), values.get());
    }

    @Override
    public void glVertexAttrib4f(int indx, float x, float y, float z, float w) {
        GL20.glVertexAttrib4f(indx, x, y, z, w);
    }

    @Override
    public void glVertexAttrib4fv(int indx, FloatBuffer values) {
        GL20.glVertexAttrib4f(indx, values.get(), values.get(), values.get(), values.get());
    }

    @Override
    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            switch (type) {
                case GL_BYTE:
                case GL_UNSIGNED_BYTE:
                    GL20.glVertexAttribPointer(indx, size, type, normalized, stride, (ByteBuffer) buffer);
                    break;
                case GL_SHORT:
                case GL_UNSIGNED_SHORT:
                    GL20.glVertexAttribPointer(indx, size, type, normalized, stride, ((ByteBuffer) buffer).asShortBuffer());
                    break;
                case GL_FLOAT:
                    GL20.glVertexAttribPointer(indx, size, type, normalized, stride, ((ByteBuffer) buffer).asFloatBuffer());
                default:
                    throw new RuntimeException("Can't use " + buffer.getClass().getName() + " with type " + type
                            + " with this method. Use ByteBuffer and one of"
                            + "GL_BYTE, GL_UNSIGNED_BYTE, GL_SHORT, GL_UNSIGNED_SHORT or GL_FLOAT for type.");
            }
        } else if (buffer instanceof FloatBuffer) {
            if (type == GL_FLOAT) {
                GL20.glVertexAttribPointer(indx, size, type, normalized, stride, (FloatBuffer) buffer);
            } else {
                throw new RuntimeException("Can't use " + buffer.getClass().getName() + " with type " + type + " with this method");
            }
        } else {
            throw new RuntimeException("Can't use " + buffer.getClass().getName() + " with this method. Use ByteBuffer instead");
        }
    }

    @Override
    public void glViewport(int x, int y, int width, int height) {
        GL11.glViewport(x, y, width, height);
    }

    @Override
    public void glDrawElements(int mode, int count, int type, int indices) {
        GL11.glDrawElements(mode, count, type, indices);
    }

    @Override
    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, int ptr) {
        GL20.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }
}
