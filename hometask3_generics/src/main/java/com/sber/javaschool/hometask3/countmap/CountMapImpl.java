package com.sber.javaschool.hometask3.countmap;

import java.util.HashMap;
import java.util.Map;


public class CountMapImpl<T> implements CountMap<T> {
    private final Map<T, Integer> map;

    public CountMapImpl(Map<T, Integer> map) {
        this();
        this.map.putAll(map);
    }

    public CountMapImpl() {
        map = new HashMap<>();
    }

    @Override
    public void add(T o) {
        map.put(o, map.getOrDefault(o, 0) + 1);
    }

    @Override
    public int getCount(T o) {
        return map.getOrDefault(o, 0);
    }

    @Override
    public int remove(T o) {
        int amountAdditions = getCount(o);
        map.remove(o);
        return amountAdditions;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        source.toMap().forEach((k, v) -> map.put(k, map.getOrDefault(k, 0) + v));
    }

    @Override
    public Map<T, Integer> toMap() {
        return new HashMap<>(map);
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
//        destination.clear();
        destination.putAll(toMap());
    }
}
