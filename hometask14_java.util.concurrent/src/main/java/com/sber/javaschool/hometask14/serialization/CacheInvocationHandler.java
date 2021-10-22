package com.sber.javaschool.hometask14.serialization;

import com.sber.javaschool.hometask14.cache.Cache;
import com.sber.javaschool.hometask14.cache.CacheType;
import com.sber.javaschool.hometask14.cache.Save;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CacheInvocationHandler implements InvocationHandler, Serializable {

    private final Object delegate;
    private final ISerializer serializer;
    private Map<Object, Object> cacheMap = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public CacheInvocationHandler(Object delegate, ISerializer serializer) {
        this.delegate = delegate;
        this.serializer = serializer;
    }

    private static String getAnnotated(Method method, Object[] args) {

        List<String> annotated = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(Save.class)) {
                int i = Integer.parseInt(parameter.getName().replaceAll("\\D", ""));
                annotated.add(args[i] + parameter.toString());
            }
        }
        return annotated.toString();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        String annotated = getAnnotated(method, args);
        if (!method.isAnnotationPresent(Cache.class)) {
            try {
                return method.invoke(delegate, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        String fileName = method.getAnnotation(Cache.class).name();
        CacheType cachetype = method.getAnnotation(Cache.class).cacheType();
        boolean isZip = method.getAnnotation(Cache.class).zip();

        if (cachetype.equals(CacheType.FILE)) {
            cacheMap = serializer.load(fileName, isZip);
        }
        //Block shared resource
        lock.lock();
        try {
            if (!cacheMap.containsKey(annotated)) {
                Object invoke = null;
                try {
                    invoke = method.invoke(delegate, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                cacheMap.put(annotated, invoke);


                if (cachetype.equals(CacheType.FILE)) {
                    synchronized (this) {
                        serializer.save(cacheMap, fileName, isZip);
                    }
                }
                return invoke;
            } else {
                // cached successfully
                return cacheMap.get(annotated);
            }
        } finally {
            lock.unlock();
        }
    }


}
