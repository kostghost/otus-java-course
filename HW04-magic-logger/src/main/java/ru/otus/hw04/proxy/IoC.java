package ru.otus.hw04.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        private final T instance;
        private final static Logger LOGGER = new Logger();

        LogInvocationHandler(T instance) {
            this.instance = instance;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log originalClassLogAnnotation = instance.getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(Log.class);

            if (originalClassLogAnnotation != null) {
                LOGGER.logArgs(method, args);
            }

            return method.invoke(instance, args);
        }

        @Override
        public String toString() {
            return "LogInvocationHandler{" +
                    "instance=" + instance +
                    '}';
        }
    }
}

