package com.sber.javaschool.hometask16.dao;

import java.util.List;
import java.util.Map;

public interface SourceDao {

    /**
     * Добавить список результатов новых вычислений
     *
     * @param calculations Map<Integer, Long>
     */
    void addFibonachi(Map<Integer, Long> calculations);

    /**
     * Получить список закешированных вычислений
     *
     * @param n input number
     * @return
     */
    List<Integer> findFibonachi(int n);

    /**
     * Пуолучить закешированное значение числа фибоначи
     *
     * @param n
     * @return fibonachi number
     */
    long findFibonachiByNumber(int n);

    /**
     * Добавить новое число
     *
     * @param n
     * @param result
     */
    void addFibonachi(int n, long result);

    /**
     * Получить все закешированные числа
     *
     * @return
     */
    List<Integer> findAll();

    boolean clear();
}
