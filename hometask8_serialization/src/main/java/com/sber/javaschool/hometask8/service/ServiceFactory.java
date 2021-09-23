package com.sber.javaschool.hometask8.service;

import com.sber.javaschool.hometask8.serialization.CacheInvocationHandler;
import com.sber.javaschool.hometask8.serialization.ISerializer;

import java.lang.reflect.Proxy;

public class ServiceFactory {
    public static Service create() {
        return new ServiceImpl();
    }

    public static Service createCached(ISerializer serializer) {
        Service delegate = new ServiceImpl();
        return (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheInvocationHandler(delegate, serializer));
    }
}
