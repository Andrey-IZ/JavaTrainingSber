package com.sber.javaschool.hometask7;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Classloader с измененным порядком загрузки класса: сначала загружает сам,
 * в случае ошибки - передает загрузку родительскому классу.
 */
public class MyClassLoader extends URLClassLoader {
    public MyClassLoader(URL[] urls) {
        super(urls);
    }

    public Class<?> loadClass(String className) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(className);
        try {
            if (clazz == null) {
                clazz = findClass(className);
            }
        } catch (ClassNotFoundException e) {
            clazz = super.loadClass(className);
        }
        return clazz;
    }
}
