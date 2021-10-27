package com.sber.javaschool.hometask16.proxy;

import com.sber.javaschool.hometask16.cache.DBCalculatorHandler;
import com.sber.javaschool.hometask16.cache.LRUCacheDB;
import com.sber.javaschool.hometask16.calculator.Calculator;

import java.lang.reflect.Proxy;

public class CacheProxy {
    public static Calculator cache(Calculator service, LRUCacheDB cache) {
        return (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                service.getClass().getInterfaces(), new DBCalculatorHandler(service, cache));
    }
}
