package com.sber.javaschool.hometask16.calculator;

import com.sber.javaschool.hometask16.cache.LRUCacheDB;
import com.sber.javaschool.hometask16.proxy.CacheProxy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CalculatorTest {

    private static List<Integer> testDataList;

    @BeforeAll
    static void setUp() {
        testDataList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            testDataList.add((int) CalculatorImpl.calc(i));
        }
    }

    @Test
    void fibonachi() {
        var cache = Mockito.spy(new LRUCacheDB(15));
        var calc = CacheProxy.cache(new CalculatorImpl(), cache);
        List<Integer> fibonachi = calc.fibonachi(15);
        var expected = testDataList.stream().limit(15).toArray(Integer[]::new);
        var actual = fibonachi.toArray(new Integer[0]);
        assertArrayEquals(expected, actual);
    }

//    @Test
//    void load_cache() {
//        var cache = Mockito.spy(new LRUCacheDB(15));
//        var calc = CacheProxy.cache(new CalculatorImpl(), cache);
//        List<Integer> fibonachi = calc.fibonachi(15);
//        var expected = testDataList.stream().limit(15).toArray(Integer[]::new);
//        var actual = fibonachi.toArray(new Integer[0]);
//        assertArrayEquals(expected, actual);
//    }
}