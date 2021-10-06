package com.sber.javaschool.hometask13;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Из %JAVA_HOME%\bin запустить jvisualvm, установить плагин: Visual GC
 * Запустить приложение создающее много объектов с разными GC,
 * посмотреть в jvisualvm как заполняются объекты в разных областях памяти(heap)
 */
public class MainTestGC {
    private static final Collection<Object> collection = new ArrayList<>();
    private static Object object;

    public static void main(String[] args) throws InterruptedException {
        try {
            while (true) {
//                Thread.sleep(100);
//                collection.add(new byte[1024 * 1024]);

                object = new byte[1024 * 1024];
            }
        } catch (OutOfMemoryError error) {
            error.printStackTrace();
            collection.clear();
        }
    }
}
