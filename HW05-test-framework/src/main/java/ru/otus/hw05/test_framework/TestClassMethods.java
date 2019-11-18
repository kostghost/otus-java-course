package ru.otus.hw05.test_framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

import ru.otus.hw05.test_framework.annotations.After;
import ru.otus.hw05.test_framework.annotations.AfterAll;
import ru.otus.hw05.test_framework.annotations.Before;
import ru.otus.hw05.test_framework.annotations.BeforeAll;
import ru.otus.hw05.test_framework.annotations.Test;

class TestClassMethods {
    private final List<Method> testMethods;
    private final List<Method> beforeMethods;
    private final List<Method> afterMethods;
    private final List<Method> beforeAllMethods;
    private final List<Method> afterAllMethods;

    public <T extends Annotation> TestClassMethods(Class clazz) {
        this.testMethods = getMethodsWithAnnotation(clazz, Test.class);
        this.beforeMethods = getMethodsWithAnnotation(clazz, Before.class);
        this.afterMethods = getMethodsWithAnnotation(clazz, After.class);
        this.beforeAllMethods = getMethodsWithAnnotation(clazz, BeforeAll.class);
        this.afterAllMethods = getMethodsWithAnnotation(clazz, AfterAll.class);
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public List<Method> getBeforeMethods() {
        return beforeMethods;
    }

    public List<Method> getAfterMethods() {
        return afterMethods;
    }

    public List<Method> getBeforeAllMethods() {
        return beforeAllMethods;
    }

    public List<Method> getAfterAllMethods() {
        return afterAllMethods;
    }

    private static <T extends Annotation> List<Method> getMethodsWithAnnotation(Class clazz, Class<T> annotationClass) {
        return List.of(clazz.getDeclaredMethods()).stream()
                .filter(x -> x.getDeclaredAnnotation(annotationClass) != null)
                .collect(Collectors.toList());
    }
}
