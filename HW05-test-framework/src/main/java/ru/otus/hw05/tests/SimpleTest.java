package ru.otus.hw05.tests;

import ru.otus.hw05.test_framework.annotations.After;
import ru.otus.hw05.test_framework.annotations.AfterAll;
import ru.otus.hw05.test_framework.annotations.Before;
import ru.otus.hw05.test_framework.annotations.BeforeAll;
import ru.otus.hw05.test_framework.annotations.Test;

public class SimpleTest {

    public static String staticValue = "not initialized";

    @Before
    public void setUp() {
        System.out.println("Before");
        System.out.println("static value:" + staticValue);
    }

    @Test
    public void testSum() {
        System.out.println("TestSum");
        System.out.println("static value:" + staticValue);
    }
    @Test
    public void testMult() {
        System.out.println("TestMult");
        System.out.println("static value:" + staticValue);
    }

    @After
    public void cleanUp() {
        System.out.println("After");
        System.out.println("static value:" + staticValue);
    }

    @BeforeAll
    public static void setupClass() {
        staticValue = "initialized!";
        System.out.println("BeforeAll");
        System.out.println("static value:" + staticValue);
    }

    @AfterAll
    public static void cleanUpClass() {
        System.out.println("AfterAll");
        staticValue = "deleted";
    }


}
