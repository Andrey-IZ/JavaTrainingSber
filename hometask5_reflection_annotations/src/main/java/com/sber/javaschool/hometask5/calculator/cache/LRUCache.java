package com.sber.javaschool.hometask5.calculator.cache;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;


public class LRUCache<K, V> extends LinkedHashMap<K, V> implements Serializable {
    private final int capacity;

    public LRUCache(int initialCapacity) {
        super(initialCapacity + 1, 1f, true);
        capacity = initialCapacity;
    }

    public V find(K key) {
        return super.get(key);
    }

    public void set(K key, V value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
