package ru.otus.hw08;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.hw08.examples.ClassWithCollections;
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
    void nullTest() {
        assert false;
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
    void listTest() {
        var list = new ArrayList<String>();
        list.add("hello");
        list.add("world");

        var map = new HashMap<String, Integer>();
        map.put("Russia", 1);
        map.put("NoRussia", 0);

        var obj = new ClassWithCollections(list, map);
        System.out.println("before: " + obj);

        String json = myJsonObjectWriter.toJson(obj);
        System.out.println("json: " + json);
    }
}