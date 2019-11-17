package ru.otus.hw04.calculator;

import ru.otus.hw04.logger.Log;

public class IntCalculatorImpl implements IntCalculator {

    @Log
    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }
}
