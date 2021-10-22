package com.sber.javaschool.hometask14.proxy;

import com.sber.javaschool.hometask14.serialization.CacheInvocationHandler;
import com.sber.javaschool.hometask14.serialization.ISerializer;
import com.sber.javaschool.hometask14.service.Service;

import java.lang.reflect.Proxy;

public class CacheProxy {
    private final ISerializer serializer;

    public CacheProxy(ISerializer serializer) {
        this.serializer = serializer;
    }

    public Service cache(Service service) {
        return (Service) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                service.getClass().getInterfaces(),
                new CacheInvocationHandler(service, serializer));
    }
}
