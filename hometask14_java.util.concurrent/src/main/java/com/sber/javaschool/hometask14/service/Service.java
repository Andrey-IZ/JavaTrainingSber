package com.sber.javaschool.hometask14.service;

import com.sber.javaschool.hometask14.cache.Cache;
import com.sber.javaschool.hometask14.cache.CacheType;
import com.sber.javaschool.hometask14.cache.Save;

public interface Service {
    @Cache(cacheType = CacheType.IN_MEMORY, limit = 20)
    double doHardWorkInMemory(@Save String name, Integer value);

    @Cache(cacheType = CacheType.FILE)
    double doHardWorkFile(String name, @Save Integer value);

    @Cache(name = "One", cacheType = CacheType.FILE, limit = 100_000, zip = true)
    double doHardWorkZip(@Save String name, @Save Integer value);
}
