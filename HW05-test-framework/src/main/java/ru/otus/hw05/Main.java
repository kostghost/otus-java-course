package ru.otus.hw05;


import ru.otus.hw05.test_framework.TestFramework;
import ru.otus.hw05.tests.SimpleTest;

public class Main {
    public static void main(String[] args) {
        String testsPath = SimpleTest.class.getName();

        TestFramework.startTests(testsPath);
    }
}
