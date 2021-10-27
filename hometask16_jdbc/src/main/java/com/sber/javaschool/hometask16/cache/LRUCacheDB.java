package com.sber.javaschool.hometask16.cache;

import com.sber.javaschool.hometask16.dao.SourceDao;

import java.util.function.Function;

public class LRUCacheDB {
    private final LRUCache<Integer, Long> cacheMap;

    public LRUCacheDB(int capacity) {
        cacheMap = new LRUCache<>(capacity);
    }

    public Long find(Integer key) {
        return cacheMap.find(key);
    }

    public void set(Integer key, Long value, Function<Integer, Long> function) {
        for (int i = 0; i < key - 1; i++) {
            if (!cacheMap.containsKey(key)) {
                cacheMap.set(key, function.apply(key));
            }
        }
        cacheMap.set(key, value);
    }

    public void saveToDB(SourceDao repository) {
        repository.addFibonachi(cacheMap);
    }

    public boolean loadFromDB(SourceDao repository) {
        var list = repository.findAll();
        for (int i = 0, listSize = list.size(); i < listSize; i++) {
            cacheMap.put(i, Long.valueOf(list.get(i)));
        }
        return true;
    }
}
