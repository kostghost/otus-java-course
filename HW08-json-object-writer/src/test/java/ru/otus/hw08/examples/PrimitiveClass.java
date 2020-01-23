package ru.otus.hw08.examples;


import java.util.Objects;

public class PrimitiveClass {
    private final int primitiveIntValue;
    private final String primitiveStringValue;

    public PrimitiveClass(int primitiveIntValue, String primitiveStringValue) {
        this.primitiveIntValue = primitiveIntValue;
        this.primitiveStringValue = primitiveStringValue;
    }

    @Override
    public String toString() {
        return "PrimitiveClass{" +
                "primitiveIntValue=" + primitiveIntValue +
                ", primitiveStringValue='" + primitiveStringValue + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PrimitiveClass that = (PrimitiveClass) o;
        return primitiveIntValue == that.primitiveIntValue &&
                Objects.equals(primitiveStringValue, that.primitiveStringValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primitiveIntValue, primitiveStringValue);
    }
}