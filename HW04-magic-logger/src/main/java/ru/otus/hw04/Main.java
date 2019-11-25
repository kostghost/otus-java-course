package ru.otus.hw04;

import ru.otus.hw04.calculator.IntCalculator;
import ru.otus.hw04.calculator.IntCalculatorImpl;
import ru.otus.hw04.proxy.IoC;

public class Main {
    public static void main(String[] args) {
        IoC ioc = new IoC();
        IntCalculator calculator = (IntCalculator) ioc.createInstance(IntCalculatorImpl.class);

        int sum = calculator.sum(10, 5);
        System.out.println("sum: " + sum);
        calculator.mult(2, 3);
        calculator.mult(10, 1, -1);
        calculator.sum(10, 20, 30);
        calculator.sum(20, 30, 40);
        calculator.sum(10, 30);

    }
}
