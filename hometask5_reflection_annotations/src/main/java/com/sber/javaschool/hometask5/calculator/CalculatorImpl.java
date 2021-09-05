package com.sber.javaschool.hometask5.calculator;

/**
 * Задача 1:
 * Имплементировать интерфейс Calculator в классе CalculatorImpl
 */
public class CalculatorImpl implements Calculator {
    @Override
    public long calc(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("It mustn't be negative number");
        }
        long factorial = 1L;
        int count = 1;
        while (count <= number) {
            factorial *= count++;
        }
        return factorial;
    }
}
