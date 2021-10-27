package com.sber.javaschool.hometask16.calculator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculatorImpl implements Calculator {

    public List<Integer> fibonachi(int n) {
        return IntStream.range(0, n).map(i -> (int) calc(i)).boxed().collect(Collectors.toList());
    }

    public static long calc(int number) {
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
