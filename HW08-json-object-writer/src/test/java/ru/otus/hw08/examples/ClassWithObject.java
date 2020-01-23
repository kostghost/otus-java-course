package ru.otus.hw08.examples;

import java.util.Objects;

public class ClassWithObject {
    private final int primitiveIntValue;
    private final PrimitiveClass primitiveObject;


    public ClassWithObject(int primitiveIntValue, PrimitiveClass primitiveObject) {
        this.primitiveIntValue = primitiveIntValue;
        this.primitiveObject = primitiveObject;
    }

    @Override
    public String toString() {
        return "ClassWithObject{" +
                "primitiveIntValue=" + primitiveIntValue +
                ", primitiveObject=" + primitiveObject +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClassWithObject)) {
            return false;
        }
        ClassWithObject that = (ClassWithObject) o;
        return primitiveIntValue == that.primitiveIntValue &&
                Objects.equals(primitiveObject, that.primitiveObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primitiveIntValue, primitiveObject);
    }
}
