package com.sber.javaschool.hometask16.cache;

import com.sber.javaschool.hometask16.dao.SourceDao;
import com.sber.javaschool.hometask16.interfaces.Cachable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBCalculatorHandler implements InvocationHandler {
    private final Object delegate;
    private SourceDao sourceDao;
    private final LRUCacheDB cache;
    private final Map<Class<? extends SourceDao>, SourceDao> daoMap = new HashMap<>();
    private final Lock lock = new ReentrantLock();


    public DBCalculatorHandler(Object delegate, LRUCacheDB cache) {
        this.delegate = delegate;
        this.cache = cache;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cachable.class)) {
            try {
                return method.invoke(delegate, args);
            } catch (IllegalArgumentException exception) {
                System.out.println("Invoke handler error: " + exception);
                return null;
            }
        }

        var annotationCache = method.getAnnotation(Cachable.class);
        Class<? extends SourceDao> daoClass = annotationCache.value();

        //Block shared resource
        lock.lock();
        try {
            var d = daoClass.getConstructor(null).newInstance();
            SourceDao sourceDao = daoMap.getOrDefault(daoClass, d);
            cache.loadFromDB(sourceDao);
            var num = args.length == 0 ? 0 : (Integer) args[0];
            Long number = cache.find(num);
            if (number != null) {
                Long fiboNumber;
                try {
                    fiboNumber = (Long) method.invoke(delegate, args);
                } catch (IllegalAccessException | InvocationTargetException | ClassCastException e) {
                    return 0;
                }
                cache.set(num, fiboNumber, integer -> {
                    try {
                        return (Long) method.invoke(delegate, integer);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return 0L;
                });

                synchronized (this) {
                    cache.saveToDB(sourceDao);
                }
                return fiboNumber;
            } else {
                // cached successfully
                return number;
            }
        } finally {
            lock.unlock();
        }
    }
}
