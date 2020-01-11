package ru.otus.hw08.examples;

import java.util.List;
import java.util.Map;

public class ClassWithCollections {
    private final List<String> list;
    private final Map<String, Integer> map;

    public ClassWithCollections(List<String> list, Map<String, Integer> map) {
        this.list = list;
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public Map<String, Integer> getMap() {
        return map;
    }
}
