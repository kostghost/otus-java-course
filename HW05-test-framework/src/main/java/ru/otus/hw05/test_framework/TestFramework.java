package ru.otus.hw05.test_framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestFramework {
    public static void startTests(String classPath) {
        try {
            Class<?> clazz = Class.forName(classPath);
            Constructor<?> constructor = clazz.getConstructor();

            TestClassMethods testClassMethods = new TestClassMethods(clazz);
            simpleChecks(testClassMethods);

            for (Method testMethod : testClassMethods.getTestMethods()) {
                Object instance = constructor.newInstance();

                testClassMethods.getBeforeMethods().forEach(x -> {
                    try {
                        x.invoke(instance);
                    } catch (Exception e){
                        throw new RuntimeException("");
                    }
                });
                testMethod.invoke(instance);
                testClassMethods.getAfterMethods().forEach(x -> {
                    try {
                        x.invoke(instance);
                    } catch (Exception e){
                        throw new RuntimeException("");
                    }
                });
            }



        } catch (Exception e) {
            throw new RuntimeException("Конечно же, эксепшОны обработали", e);
        }
    }

    private static void simpleChecks(TestClassMethods testMethods) {

    }




}
