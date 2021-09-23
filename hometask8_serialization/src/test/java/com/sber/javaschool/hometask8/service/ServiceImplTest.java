package com.sber.javaschool.hometask8.service;

import com.sber.javaschool.hometask8.cache.Cache;
import com.sber.javaschool.hometask8.cache.CacheType;
import com.sber.javaschool.hometask8.serialization.ISerializer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceImplTest {

    private String cacheName;
    private boolean cachedAsZip;
    private ISerializer serializer;
    private Service service;
    private CacheType cacheType;

    @Test
    void doHardWorkInMemoryTest() throws NoSuchMethodException {
        Cache cacheAnnotation = Service.class.getDeclaredMethod("doHardWorkInMemory", String.class, Integer.class)
                .getAnnotation(Cache.class);
        cacheName = cacheAnnotation.name();
        cachedAsZip = cacheAnnotation.zip();
        cacheType = cacheAnnotation.cacheType();
        serializer = mock(ISerializer.class);
        service = ServiceFactory.createCached(serializer);

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

        service.doHardWorkInMemory("I'm working", 1);
        verify(serializer, times(1)).load(cacheName, false);
        verify(serializer, times(1)).save(any(), eq(cacheName), eq(false));

        service.doHardWorkInMemory("I'm working", 1);
        verify(serializer, times(2)).load(cacheName, false);
        // it shouldn't call save one more time
        verify(serializer, times(1)).save(any(), anyString(), anyBoolean());
    }

    @Test
    void doHardWorkFileTest() throws NoSuchMethodException {
        Cache cacheAnnotation = Service.class.getDeclaredMethod("doHardWorkFile", String.class, Integer.class)
                .getAnnotation(Cache.class);
        cacheName = cacheAnnotation.name();
        cachedAsZip = cacheAnnotation.zip();
        cacheType = cacheAnnotation.cacheType();
        serializer = mock(ISerializer.class);
        service = ServiceFactory.createCached(serializer);

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

        service.doHardWorkFile("I'm working", 1);
        verify(serializer, times(1)).load(cacheName, false);
        verify(serializer, times(1)).save(any(), eq(cacheName), eq(false));

        service.doHardWorkFile("I'm working", 1);
        verify(serializer, times(2)).load(cacheName, false);
        // it shouldn't call save one more time
        verify(serializer, times(1)).save(any(), anyString(), anyBoolean());
    }

    @Test
    void doHardWorkZipTest() throws NoSuchMethodException {
        Cache cacheAnnotation = Service.class.getDeclaredMethod("doHardWorkZip", String.class, Integer.class)
                .getAnnotation(Cache.class);
        cacheName = cacheAnnotation.name();
        cachedAsZip = cacheAnnotation.zip();
        cacheType = cacheAnnotation.cacheType();
        serializer = mock(ISerializer.class);
        service = ServiceFactory.createCached(serializer);

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

        service.doHardWorkZip("I'm working", 1);
        verify(serializer, times(1)).load(cacheName, true);
        verify(serializer, times(1)).save(any(), eq(cacheName), eq(true));

        service.doHardWorkZip("I'm working", 1);
        verify(serializer, times(2)).load(cacheName, true);
        // it shouldn't call save one more time
        verify(serializer, times(1)).save(any(), anyString(), anyBoolean());
    }
}