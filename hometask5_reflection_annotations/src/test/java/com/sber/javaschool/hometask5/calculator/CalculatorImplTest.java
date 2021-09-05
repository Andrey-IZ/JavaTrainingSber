package com.sber.javaschool.hometask5.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorImplTest {

    @Test
    void calc() {
        var calculator = new CalculatorImpl();
        assertEquals(120, calculator.calc(5));
        assertEquals(1, calculator.calc(0));
        assertThrows(IllegalArgumentException.class, () -> calculator.calc(-1));
    }
}