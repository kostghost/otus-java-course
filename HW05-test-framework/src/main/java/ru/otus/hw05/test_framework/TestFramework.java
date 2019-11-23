package ru.otus.hw05.test_framework;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import ru.otus.hw05.test_framework.statistics.TestStatistics;

public class TestFramework {
    public static void runTests(String classPath) {
        try {
            Class<?> clazz = Class.forName(classPath);
            Constructor<?> constructor = clazz.getConstructor();

            TestClassMethods testClassMethods = new TestClassMethods(clazz);
            simpleChecks(testClassMethods);

            runMethods(null, testClassMethods.getBeforeAllMethods());

            TestStatistics statistics = new TestStatistics();

            for (Method testMethod : testClassMethods.getTestMethods()) {
                Object instance = constructor.newInstance();

                // считаю, что если exception произошел в before/after
                // то это ошибка и тестирование должно прерваться
                runMethods(instance, testClassMethods.getBeforeMethods());
                runPreparedTestAndWriteStatistic(testMethod, instance, statistics);
                runMethods(instance, testClassMethods.getAfterMethods());
            }

            runMethods(null, testClassMethods.getAfterAllMethods());
            System.out.println(statistics.getFormattedStatistic());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static void runPreparedTestAndWriteStatistic(Method testMethod, Object instance,
                                                         TestStatistics statistics) {
        long runTimeMs = System.currentTimeMillis();
        try {
            testMethod.invoke(instance);
            long durationMs = System.currentTimeMillis() - runTimeMs;
            statistics.addPassed(testMethod.getName(), durationMs);

        } catch (Exception ex) {
            long durationMs = System.currentTimeMillis() - runTimeMs;
            statistics.addFailed(testMethod.getName(), durationMs, ex);
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
