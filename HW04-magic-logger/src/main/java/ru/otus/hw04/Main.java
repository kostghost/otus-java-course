package ru.otus.hw04;

import ru.otus.hw04.calculator.IntCalculator;
import ru.otus.hw04.calculator.IntCalculatorImpl;
import ru.otus.hw04.proxy.IoC;

public class Main {
    public static void main(String[] args) {
        var ioc = new IoC();
        IntCalculator calculator = (IntCalculator) ioc.createInstance(IntCalculatorImpl.class);

        int sum = calculator.sum(10, 5);
        System.out.println("sum: " + sum);

        int mult = calculator.mult(2, 3);
        System.out.println("mult: " + mult);
    }
}
