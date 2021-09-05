package com.sber.javaschool.hometask5.calculator.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static java.lang.String.*;

/**
 * Задача 5:
 * Реализовать кэширующий прокси
 */
public class CalculatorHandler implements InvocationHandler {
    private final Object delegate;
    private final LRUCacheExt<Integer, Long> cache;

    public CalculatorHandler(Object delegate) {
        this.delegate = delegate;
        cache = new LRUCacheExt<>(10);
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {

        if (!method.isAnnotationPresent(Cache.class)) {
            try {
                return method.invoke(delegate, args);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invoke handler error: " + exception);
                return null;
            }
        }

        var annotationCache = method.getAnnotation(Cache.class);
        String fileName = annotationCache.name();
        CacheType cacheType = annotationCache.cacheType();
        cache.loadFromFile(fileName);

        Integer arg = (args.length == 1) ? (Integer) args[0] : 0;

        var value = cache.find(arg, cacheType);
        if (value == null) {
            value = (Long) method.invoke(delegate, args);
            cache.set(arg, value, cacheType);
            System.out.format(" %s не найдено, помещаем в кеш (%s %s)\n",
                    format("%d->%d", arg, value),
                    cacheType.toString(),
                    cacheType == CacheType.FILE ? fileName : "");
        } else {
            System.out.format(" %s, взято из кэша (%s %s)\n",
                    format("%d->%d", arg, value),
                    cacheType.toString(),
                    cacheType == CacheType.FILE ? fileName : "");
        }

        synchronized (this) {
            cache.saveToFile(fileName);
        }
        return value;
    }
}
