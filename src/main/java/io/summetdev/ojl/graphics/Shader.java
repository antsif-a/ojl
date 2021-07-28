package io.summetdev.ojl.graphics;

import java.io.*;
import java.nio.file.*;

import static org.lwjgl.opengl.GL30C.*;

public class Shader extends GLStruct {
    public Shader(int id) {
        super(id);
    }

    public static Shader create(File vertexShader, File fragmentShader) {
        try {
            return create(Files.readString(vertexShader.toPath()), Files.readString(fragmentShader.toPath()));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: ", e);
        }
    }

    public static Shader create(String vertexShader, String fragmentShader) {
        int programId = glCreateProgram();
        int vertexShaderId = compileShader(vertexShader, ShaderType.vertex);
        int fragmentShaderId = compileShader(fragmentShader, ShaderType.fragment);

        glAttachShader(programId, vertexShaderId);
        glAttachShader(programId, fragmentShaderId);
        glLinkProgram(programId);

        int[] status = new int[1];
        glGetProgramiv(programId, GL_LINK_STATUS, status);
        if (status[0] != 1) {
            throw new RuntimeException(glGetProgramInfoLog(programId));
        }

        return new Shader(programId);
    }

    private static int compileShader(String shader, ShaderType type) {
        int shaderId = glCreateShader(type.code);
        glShaderSource(shaderId, shader);
        glCompileShader(shaderId);

        int[] status = new int[1];
        glGetShaderiv(shaderId, GL_COMPILE_STATUS, status);
        if (status[0] != 1) {
            throw new RuntimeException(glGetShaderInfoLog(shaderId));
        }

        return shaderId;
    }

    public void use() {
        glUseProgram(id);
    }

    public void delete() {
        glDeleteProgram(id);
    }

    public void validate() {
        glValidateProgram(id);
    }

    public enum ShaderType {
        vertex(GL_VERTEX_SHADER), fragment(GL_FRAGMENT_SHADER);

        public int code;

        ShaderType(int code) {
            this.code = code;
        }
    }
}
