package com.sber.javaschool.hometask3.countmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Задание 3.1
 * Параметризовать CountMap и реализовать его
 */

public class MainApp {
    public static void main(String[] args) {
        // Notes: add VM options in configurations such as "-ea" to activate assertions
        testAdd();
        testAddAll();
        testRemove();
        testToMap();
        testToMapDestination();
    }

    private static void testRemove() {
        CountMap<Integer> countMap = new CountMapImpl<>();
        Map<Integer, Integer> map = new HashMap<>();
        initCountMap(countMap, map);

        assert countMap.getCount(5) == map.get(5);
        countMap.remove(5);
        assert countMap.getCount(5) != map.get(5);
        assert countMap.getCount(6) == map.get(6);
        countMap.remove(6);
        assert countMap.getCount(6) != map.get(6);
        countMap.remove(10);
        assert countMap.getCount(10) != map.get(10);
        assert countMap.size() == 0;
    }

    private static void testToMap() {
        CountMap<Integer> countMap = new CountMapImpl<>();
        Map<Integer, Integer> map = new HashMap<>();
        initCountMap(countMap, map);
        assert countMap.toMap().equals(map);
    }

    private static void initCountMap(CountMap<Integer> countMap, Map<Integer, Integer> map) {
        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);
        map.put(5, 2);
        map.put(6, 1);
        map.put(10, 3);
    }

    private static void testToMapDestination() {
        CountMap<Integer> countMap = new CountMapImpl<>();
        Map<Integer, Integer> destination = new HashMap<>();
        initCountMap(countMap, destination);
        destination.clear();
        countMap.toMap(destination);

        assert countMap.toMap().equals(destination);
    }

    private static void testAddAll() {
        CountMap<Integer> countMap = new CountMapImpl<>();
        Map<Integer, Integer> map = new HashMap<>();
        initCountMap(countMap, map);

        var countMapNew = new CountMapImpl<Integer>();
        countMapNew.addAll(countMap);

        assert countMapNew.getCount(5) == map.get(5);
        assert countMapNew.getCount(6) == map.get(6);
        assert countMapNew.getCount(10) == map.get(10);

        countMapNew.addAll(countMap);

        assert countMapNew.getCount(5) == map.get(5) + map.get(5);
        assert countMapNew.getCount(6) == map.get(6) + map.get(6);
        assert countMapNew.getCount(10) == map.get(10) + map.get(10);
    }

    private static void testAdd() {
        CountMap<Integer> countMap = new CountMapImpl<>();
        Map<Integer, Integer> map = new HashMap<>();
        initCountMap(countMap, map);

        assert countMap.getCount(5) == map.get(5);
        assert countMap.getCount(6) == map.get(6);
        assert countMap.getCount(10) == map.get(10);
    }
}
