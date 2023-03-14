package dataStructures;

import java.util.Objects;

public class Sizes<T> {
    private T sizeX, sizeY, sizeZ;

    public Sizes(T sizeX, T sizeY, T sizeZ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
    }

    public T sizeX() {
        return sizeX;
    }

    public void setSizeX(T sizeX) {
        this.sizeX = sizeX;
    }

    public T sizeY() {
        return sizeY;
    }

    public void setSizeY(T sizeY) {
        this.sizeY = sizeY;
    }

    public T sizeZ() {
        return sizeZ;
    }

    public void setSizeZ(T sizeZ) {
        this.sizeZ = sizeZ;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sizes<?> sizes = (Sizes<?>) object;
        return sizeX.equals(sizes.sizeX) && sizeY.equals(sizes.sizeY) && sizeZ.equals(sizes.sizeZ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeX, sizeY, sizeZ);
    }
}
