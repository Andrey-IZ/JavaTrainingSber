package com.sber.javaschool.hometask5.calculator;

import com.sber.javaschool.hometask5.calculator.cache.Cache;
import com.sber.javaschool.hometask5.calculator.cache.CacheType;
import com.sber.javaschool.hometask5.calculator.metric.Metric;

public interface Calculator {
    /**
     * Расчет факториала числа.
     *
     * @param number - число для расчета
     * @return факториал числа
     */
    @Cache
    @Metric
    long calc(int number) throws IllegalArgumentException;
}
