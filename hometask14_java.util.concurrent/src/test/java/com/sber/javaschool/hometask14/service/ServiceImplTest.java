package com.sber.javaschool.hometask14.service;

import com.sber.javaschool.hometask14.cache.Cache;
import com.sber.javaschool.hometask14.cache.CacheType;
import com.sber.javaschool.hometask14.proxy.CacheProxy;
import com.sber.javaschool.hometask14.serialization.ISerializer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ServiceImplTest {

    private String cacheName;
    private boolean cachedAsZip;
    private ISerializer serializer;
    private Service service;
    private CacheType cacheType;

    @Test
    void doHardWorkInMemoryTest() throws NoSuchMethodException {
        initFieldsFromAnnotation("doHardWorkInMemory");

        assertFalse(cachedAsZip);
        assertEquals(cacheType, CacheType.IN_MEMORY);

        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put("[1java.lang.Integer arg1]=1.0", new Object());

        when(serializer.load(cacheName, false)).thenReturn(expectedMap);
        doAnswer(invocationOnMock -> {
            var actual = invocationOnMock.getArgument(0);
            assertEquals(expectedMap, actual);
            return null;
        }).when(serializer).save(any(Map.class), eq(cacheName), eq(false));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Collection<Future<Double>> futures = new ArrayList<>();
        int inputData = 1;
        int amountAttempts = 10;
        for (int i = 0; i < amountAttempts; i++) {
            futures.add(executorService.submit(() -> service.doHardWorkInMemory("I'm working", inputData)));
        }
        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(amountAttempts, TimeUnit.MILLISECONDS))
                Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futures.stream().filter(Future::isDone).forEach(future -> {
            try {
                assertEquals(1, future.get());

                verify(serializer, never()).load(cacheName, false);
                verify(serializer, never()).save(any(), eq(cacheName), eq(false));

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        service.doHardWorkInMemory("I'm working", 1);
        verify(serializer, never()).load(cacheName, false);
        // it shouldn't call save one more time
        verify(serializer, never()).save(any(), anyString(), anyBoolean());
    }

    @Test
    void doHardWorkFileTest() throws NoSuchMethodException {
        initFieldsFromAnnotation("doHardWorkFile");

        assertFalse(cachedAsZip);
        assertEquals(cacheType, CacheType.FILE);

        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put("[1java.lang.Integer arg1]=1.0", new Object());

        when(serializer.load(cacheName, false)).thenReturn(expectedMap);
        doAnswer(invocationOnMock -> {
            var actual = invocationOnMock.getArgument(0);
            assertEquals(expectedMap, actual);
            return null;
        }).when(serializer).save(any(Map.class), eq(cacheName), eq(false));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Collection<Future<Double>> futures = new ArrayList<>();
        int inputData = 1;
        int amountAttempts = 10;
        for (int i = 0; i < amountAttempts; i++) {
            futures.add(executorService.submit(() -> service.doHardWorkFile("I'm working", inputData)));
        }
        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(amountAttempts, TimeUnit.MILLISECONDS))
                Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futures.stream().filter(Future::isDone).forEach(future -> {
            try {
                assertEquals(1, future.get());

                verify(serializer, times(amountAttempts)).load(cacheName, false);
                verify(serializer, times(1)).save(any(), eq(cacheName), eq(false));

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        service.doHardWorkFile("I'm working", 1);
        verify(serializer, times(amountAttempts + 1)).load(cacheName, false);
        // it shouldn't call save one more time
        verify(serializer, times(1)).save(any(), anyString(), anyBoolean());
    }

    @Test
    void doHardWorkZipTest() throws NoSuchMethodException {
        initFieldsFromAnnotation("doHardWorkZip");

        assertTrue(cachedAsZip);
        assertEquals(cacheType, CacheType.FILE);

        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put("[1java.lang.Integer arg1]=1.0", new Object());

        when(serializer.load(cacheName, true)).thenReturn(expectedMap);
        doAnswer(invocationOnMock -> {
            var actual = invocationOnMock.getArgument(0);
            assertEquals(expectedMap, actual);
            return null;
        }).when(serializer).save(any(Map.class), eq(cacheName), eq(false));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Collection<Future<Double>> futures = new ArrayList<>();
        int inputData = 1;
        int amountAttempts = 10;
        for (int i = 0; i < amountAttempts; i++) {
            futures.add(executorService.submit(() -> service.doHardWorkZip("I'm working", inputData)));
        }
        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(amountAttempts, TimeUnit.MILLISECONDS))
                Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        futures.stream().filter(Future::isDone).forEach(future -> {
            try {
                assertEquals(1, future.get());

                verify(serializer, times(amountAttempts)).load(cacheName, true);
                verify(serializer, times(1)).save(any(), eq(cacheName), eq(true));

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        service.doHardWorkZip("I'm working", 1);
        verify(serializer, times(amountAttempts + 1)).load(cacheName, true);
        // it shouldn't call save one more time
        verify(serializer, times(1)).save(any(), anyString(), anyBoolean());
    }

    private void initFieldsFromAnnotation(String doHardWorkZip) throws NoSuchMethodException {
        Cache cacheAnnotation = Service.class.getDeclaredMethod(doHardWorkZip, String.class, Integer.class)
                .getAnnotation(Cache.class);
        cacheName = cacheAnnotation.name();
        cachedAsZip = cacheAnnotation.zip();
        cacheType = cacheAnnotation.cacheType();
        serializer = mock(ISerializer.class);
        service = new CacheProxy(serializer).cache(ServiceFactory.create());
    }
}