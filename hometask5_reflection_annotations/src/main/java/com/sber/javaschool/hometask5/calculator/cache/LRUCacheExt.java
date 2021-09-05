package com.sber.javaschool.hometask5.calculator.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class LRUCacheExt<K, V> {
    private final Map<CacheType, LRUCache<K, V>> cacheMap;

    public LRUCacheExt(int capacity) {
        cacheMap = new EnumMap<>(CacheType.class);
        for (CacheType value : CacheType.values()) {
            cacheMap.put(value, new LRUCache<>(capacity));
        }
    }

    public V find(K key, CacheType cacheType) {
        return cacheMap.get(cacheType).find(key);
    }

    public void set(K key, V value, CacheType cacheType) {
        LRUCache<K, V> lruCache = cacheMap.get(cacheType);
        lruCache.set(key, value);
//            cacheMap.put(cacheType, lruCache);
    }

    public boolean saveToFile(String fileName) {
        if (cacheMap.get(CacheType.FILE).isEmpty()) {
            return false;
        }
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(cacheMap.get(CacheType.FILE));
        } catch (IOException e) {
            System.out.println("The writing to cache file hasn't been completed" + e);
            return false;
        }
        return true;
    }

    public boolean loadFromFile(String fileName) {
        if (cacheMap.get(CacheType.FILE).isEmpty()) {
            return false;
        }
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            LRUCache<K, V> lruCache = (LRUCache<K, V>) inputStream.readObject();
            if (lruCache != null) {
                cacheMap.replaceAll((t, v) -> lruCache);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                Files.createFile(Path.of(fileName));
                return loadFromFile(fileName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
