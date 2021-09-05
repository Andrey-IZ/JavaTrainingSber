package com.sber.javaschool.hometask5.calculator;

import com.sber.javaschool.hometask5.calculator.cache.CalculatorHandler;
import com.sber.javaschool.hometask5.calculator.metric.PerformanceProxy;

import java.lang.reflect.Proxy;

public class ProxyUtils {
    public static Calculator makeCache(Calculator calculator) {
        return (Calculator) Proxy.newProxyInstance(
                calculator.getClass().getClassLoader(),
                calculator.getClass().getInterfaces(),
                new CalculatorHandler(calculator));
    }

    public static Calculator makePerformanceMetric(Calculator calculator) {
        return (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(),
                new Class[]{Calculator.class},
                new PerformanceProxy(calculator));
    }
}
