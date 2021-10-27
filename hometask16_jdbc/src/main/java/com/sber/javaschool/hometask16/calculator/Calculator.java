package com.sber.javaschool.hometask16.calculator;

import com.sber.javaschool.hometask16.dao.H2DB;
import com.sber.javaschool.hometask16.interfaces.Cachable;

import java.util.List;

public interface Calculator {
    /**
     * @param num число для расчета
     * @return число фибоначчи
     */
    @Cachable(H2DB.class)
    List<Integer> fibonachi(int num);
}
