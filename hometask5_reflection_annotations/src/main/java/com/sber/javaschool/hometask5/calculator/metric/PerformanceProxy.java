package com.sber.javaschool.hometask5.calculator.metric;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Задача 6:
 * Создать свой аннотацию Metric. Реализовать proxy класс PerformanceProxy,
 * который в случае если метод аннотирован Metric будет выводить
 * на консоль время выполнения метода.
 */
public class PerformanceProxy implements InvocationHandler {
    private final Object delegate;

    public PerformanceProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws InvocationTargetException,
            IllegalAccessException {
        if (!method.isAnnotationPresent(Metric.class)) {
            return method.invoke(delegate, args);
        }
        long time = System.nanoTime();
        Object invoke = method.invoke(delegate, args);
        time = System.nanoTime() - time;
        System.out.println("Время работы метода: " + time + " (в наносек)");
        return invoke;
    }
}
