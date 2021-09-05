package com.sber.javaschool.hometask5.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void makeCache() {
        Calculator calculator = ProxyUtils.makeCache(new CalculatorImpl());

        assertEquals(120, calculator.calc(5));
        assertEquals(1, calculator.calc(0));
//        assertThrows(IllegalArgumentException.class, () -> calculator.calc(-1));
        assertEquals(1, calculator.calc(1));
        assertEquals(1, calculator.calc(1));
        assertEquals(1, calculator.calc(1));
        assertEquals(1, calculator.calc(1));
        assertEquals(120, calculator.calc(5));
        assertEquals(720, calculator.calc(6));
    }

    @Test
    void makePerformanceMetric() {
        Calculator calculator = ProxyUtils.makePerformanceMetric(ProxyUtils.makeCache(new CalculatorImpl()));

        assertEquals(120, calculator.calc(5));
        assertEquals(1, calculator.calc(0));
//        assertThrows(IllegalArgumentException.class, () -> calculator.calc(-1));
        assertEquals(1, calculator.calc(1));
        assertEquals(1, calculator.calc(1));
        assertEquals(1, calculator.calc(1));
        assertEquals(1, calculator.calc(1));
        assertEquals(120, calculator.calc(5));
        assertEquals(720, calculator.calc(6));
    }
}