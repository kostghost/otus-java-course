package ru.otus.hw08.examples;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassWithCollections {
    private final List<String> list;
    private final Map<String, Integer> map;
    private final Map<Integer, Integer> intMap;
    private final Integer[] array;
    private final int[] arrayTwo;
    private final Set<Integer> set;

    public ClassWithCollections(List<String> list, Map<String, Integer> map, Map<Integer, Integer> intMap,
                                Integer[] array, int[] arrayTwo, Set<Integer> set) {
        this.list = list;
        this.map = map;
        this.intMap = intMap;
        this.array = array;
        this.arrayTwo = arrayTwo;
        this.set = set;
    }

    public List<String> getList() {
        return list;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public Integer[] getArray() {
        return array;
    }

    public int[] getArrayTwo() {
        return arrayTwo;
    }

    public Map<Integer, Integer> getIntMap() {
        return intMap;
    }

    public Set<Integer> getSet() {
        return set;
    }
}
