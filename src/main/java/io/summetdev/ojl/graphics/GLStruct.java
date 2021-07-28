package io.summetdev.ojl.graphics;

public abstract class GLStruct {
    protected final int id;

    protected GLStruct(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return id == ((GLStruct) o).id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
