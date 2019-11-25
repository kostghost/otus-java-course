package ru.otus.hw04.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import ru.otus.hw04.logger.Log;
import ru.otus.hw04.logger.Logger;

public class IoC {
    public Object createInstance(Class clazz) {
        try {
            ClassLoader classLoader = clazz.getClassLoader();
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Class[] interfaces = clazz.getInterfaces();
            InvocationHandler handler = new LogInvocationHandler(instance);

            return Proxy.newProxyInstance(
                    classLoader, interfaces, handler);

        } catch (Exception e) {
            throw new RuntimeException("Случилось что-то ужасное");
        }
    }

    static class LogInvocationHandler<T> implements InvocationHandler {
        private final static Logger logger = new Logger();
        private final T instance;
        // К сожалению, все методы с одним названием храним в одной корзине т.к. hashCode от Method
        // считается только по названию метода.
        // Однако, такой словарь будет работать правильно т.к. equals учитывает еще и типы аргументов метода.
        Map<Method, Boolean> hasLogAnnotationMap = new HashMap<>();

        LogInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Boolean hasLogAnnotation;
            if (hasLogAnnotationMap.containsKey(method)) {
                hasLogAnnotation = hasLogAnnotationMap.get(method);
            } else {
                hasLogAnnotation = instance.getClass()
                        .getMethod(method.getName(), method.getParameterTypes())
                        .getAnnotation(Log.class) != null;

                hasLogAnnotationMap.put(method, hasLogAnnotation);
            }

            if (hasLogAnnotation) {
                logger.logArgs(method, args);
            }

            return method.invoke(instance, args);
        }
    }
}

