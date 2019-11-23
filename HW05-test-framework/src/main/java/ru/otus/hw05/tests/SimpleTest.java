package ru.otus.hw05.tests;

import ru.otus.hw05.test_framework.annotations.After;
import ru.otus.hw05.test_framework.annotations.AfterAll;
import ru.otus.hw05.test_framework.annotations.Before;
import ru.otus.hw05.test_framework.annotations.BeforeAll;
import ru.otus.hw05.test_framework.annotations.Test;

public class SimpleTest {

    private static int staticCounter = 0;
    private int instanceCounter = 0;

    @BeforeAll
    public static void setupClass() {
        System.out.println("BeforeAll");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testSum() {
        System.out.println("TestSum");
        addCounts();
        printCounts();
    }

    @Test
    public void testMult() throws InterruptedException {
        System.out.println("TestMult");
        addCounts();
        printCounts();
        Thread.sleep(100);
    }

    @Test
    public void testSumThereIsNoException() {
        System.out.println("testSumThereIsNoException");
        addCounts();
        printCounts();
        throw new RuntimeException("Oups");
    }

    @After
    public void cleanUp() {
        System.out.println("After");
    }

    @AfterAll
    public static void cleanUpClass() {
        System.out.println("AfterAll");
    }

    private void addCounts() {
        staticCounter++;
        instanceCounter++;
    }

    private void printCounts() {
        System.out.println("static: " + staticCounter + " instance: " + instanceCounter);
    }
}
