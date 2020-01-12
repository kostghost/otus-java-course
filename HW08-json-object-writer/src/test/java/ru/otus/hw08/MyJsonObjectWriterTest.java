package ru.otus.hw08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.hw08.examples.ClassWithCollections;
import ru.otus.hw08.examples.ClassWithComplexCollections;
import ru.otus.hw08.examples.ClassWithObject;
import ru.otus.hw08.examples.PrimitiveClass;

class MyJsonObjectWriterTest {

    private static Gson gson;
    private static MyJsonObjectWriter myJsonObjectWriter;

    @BeforeAll
    static void initClass() {
        gson = new Gson();
        myJsonObjectWriter = new MyJsonObjectWriter();
    }

    @Test
    void primitiveToJsonTest() {
        PrimitiveClass obj = new PrimitiveClass(999, "str");

        String json = myJsonObjectWriter.toJson(obj);
        System.out.println("json: " + json);

        PrimitiveClass obj2 = gson.fromJson(json, PrimitiveClass.class);
        Assertions.assertEquals(obj, obj2);
    }

    @Test
    void withObjectJsonTest() {
        ClassWithObject obj = new ClassWithObject(2222, new PrimitiveClass(999, "str"));

        String json = myJsonObjectWriter.toJson(obj);
        System.out.println("json: " + json);

        ClassWithObject obj2 = gson.fromJson(json, ClassWithObject.class);
        Assertions.assertEquals(obj, obj2);
    }

    @Test
    void emptyOrNulCollectionTest() {
        var list = new ArrayList<String>();
        list.add(null);

        var array = new Integer[]{
                0, null, 2, 3
        };

        var intMap = new HashMap<Integer, Integer>();
        intMap.put(999, null);
        intMap.put(0, 0);

        var obj = new ClassWithCollections(list, null, intMap, array, null, null);

        String json = myJsonObjectWriter.toJson(obj);

        System.out.println(json);
        var obj2 = gson.fromJson(json, ClassWithCollections.class);

        Assertions.assertArrayEquals(obj.getArray(), obj2.getArray());
        Assertions.assertArrayEquals(obj.getArrayTwo(), obj2.getArrayTwo());
        Assertions.assertEquals(obj.getList(), obj2.getList());
        Assertions.assertEquals(obj.getMap(), obj2.getMap());
        Assertions.assertEquals(obj.getIntMap(), obj2.getIntMap());
        Assertions.assertEquals(obj.getSet(), obj2.getSet());
    }

    @Test
    void collectionsTest() {
        var list = new ArrayList<String>();
        list.add("hello");
        list.add("world");

        var map = new HashMap<String, Integer>();
        map.put("Russia", 1);
        map.put("NoRussia", 0);

        var intMap = new HashMap<Integer, Integer>();
        intMap.put(999, 1);
        intMap.put(0, 0);

        var array = new Integer[]{
                0, 1, 2, 3
        };

        var arrayTwo = new int[]{
                2, 3, 4, 5
        };

        var set = new HashSet<Integer>();
        set.add(9);
        set.add(1);

        var obj = new ClassWithCollections(list, map, intMap, array, arrayTwo, set);

        String json = myJsonObjectWriter.toJson(obj);

        var obj2 = gson.fromJson(json, ClassWithCollections.class);

        Assertions.assertArrayEquals(obj.getArray(), obj2.getArray());
        Assertions.assertArrayEquals(obj.getArrayTwo(), obj2.getArrayTwo());
        Assertions.assertEquals(obj.getList(), obj2.getList());
        Assertions.assertEquals(obj.getMap(), obj2.getMap());
        Assertions.assertEquals(obj.getIntMap(), obj2.getIntMap());
        Assertions.assertEquals(obj.getSet(), obj2.getSet());

    }

    @Test
    void complexCollectionsTest() {
        var list = new ArrayList<PrimitiveClass>();
        list.add(new PrimitiveClass(21, "to"));
        list.add(new PrimitiveClass(32, "tt"));

        var map = new HashMap<String, PrimitiveClass>();
        map.put("Russia", new PrimitiveClass(11, "el"));
        map.put("NoRussia", new PrimitiveClass(12, "tw"));

        var array = new PrimitiveClass[]{
                new PrimitiveClass(45, "ff"),
                new PrimitiveClass(65, "sf")
        };

        var obj = new ClassWithComplexCollections(array, list, map);

        String json = myJsonObjectWriter.toJson(obj);

        var obj2 = gson.fromJson(json, ClassWithComplexCollections.class);

        Assertions.assertArrayEquals(obj.getPrimitiveClasses(), obj2.getPrimitiveClasses());
        Assertions.assertEquals(obj.getPrimitiveClassList(), obj2.getPrimitiveClassList());
        Assertions.assertEquals(obj.getPrimitiveClassMap(), obj2.getPrimitiveClassMap());
    }
}