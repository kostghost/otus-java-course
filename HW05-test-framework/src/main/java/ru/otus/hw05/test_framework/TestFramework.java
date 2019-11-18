package ru.otus.hw05.test_framework;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;


public class TestFramework {
    public static void runTests(String classPath) {
        try {
            Class<?> clazz = Class.forName(classPath);
            Constructor<?> constructor = clazz.getConstructor();

            TestClassMethods testClassMethods = new TestClassMethods(clazz);
            simpleChecks(testClassMethods);

            runMethods(null, testClassMethods.getBeforeAllMethods());

            for (Method testMethod : testClassMethods.getTestMethods()) {

                Object instance = constructor.newInstance();

                runMethods(instance, testClassMethods.getBeforeMethods());
                testMethod.invoke(instance);
                runMethods(instance, testClassMethods.getAfterMethods());
            }

            runMethods(null, testClassMethods.getAfterAllMethods());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static void runMethods(Object instance, List<Method> methods) {
        methods.forEach(x -> {
            try {
                x.invoke(instance);
            } catch (Exception e) {
                throw new RuntimeException("Случилась ошибка во время запуска метода " + x.getName() + "\n" + e);
            }
        });
    }


    private static void simpleChecks(TestClassMethods testMethods) {
        boolean beforeAllChecksPassed = testMethods.getBeforeAllMethods().stream()
                .allMatch(x -> {
                    int modifiers = x.getModifiers();
                    int argsCount = Array.getLength(x.getParameters());

                    return Modifier.isStatic(modifiers)
                            && !Modifier.isPrivate(modifiers)
                            && argsCount == 0;
                });

        // + Проверки других методов

        if (!beforeAllChecksPassed) {
            throw new RuntimeException("Методы, помеченные аннотацией @BeforeAll должны иметь модификаторы public " +
                    "static и не иметь аргументов");
        }
    }


}
