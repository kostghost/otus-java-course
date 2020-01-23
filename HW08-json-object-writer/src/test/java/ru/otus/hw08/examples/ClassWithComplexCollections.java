package ru.otus.hw08.examples;

import java.util.List;
import java.util.Map;

public class ClassWithComplexCollections {
    private final PrimitiveClass[] primitiveClasses;
    private final List<PrimitiveClass> primitiveClassList;
    private final Map<String, PrimitiveClass> primitiveClassMap;

    public ClassWithComplexCollections(PrimitiveClass[] primitiveClasses, List<PrimitiveClass> primitiveClassList,
                                       Map<String, PrimitiveClass> primitiveClassMap) {
        this.primitiveClasses = primitiveClasses;
        this.primitiveClassList = primitiveClassList;
        this.primitiveClassMap = primitiveClassMap;
    }

    public PrimitiveClass[] getPrimitiveClasses() {
        return primitiveClasses;
    }

    public List<PrimitiveClass> getPrimitiveClassList() {
        return primitiveClassList;
    }

    public Map<String, PrimitiveClass> getPrimitiveClassMap() {
        return primitiveClassMap;
    }
}
