package ru.otus.hw04.logger;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {
    public void logArgs(Method method, Object[] args) {
        var argsString = List.of(args).stream()
                .map(x -> x.toString())
                .collect(Collectors.joining(", "));

        var paramString = List.of(method.getParameters()).stream()
                .map(x -> x.toString())
                .collect(Collectors.joining(", "));

        System.out.println(
                "executed method: " + method.getReturnType().toString() + " " + method.getName() +
                        "(" + paramString + ") " +
                        "args: " + argsString);
    }
}
