package com.sber.javaschool.hometask3.countmap;

import java.util.Map;

public interface CountMap<T> {
    /**
     * добавляет элемент в этот контейнер.
     *
     * @param o элемент
     */
    void add(T o);

    /**
     * @param o элемент
     * @return количество добавлений данного элемента
     */
    int getCount(T o);

    /**
     * Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
     *
     * @param o элемент
     * @return количество его добавлений(до удаления)
     */
    int remove(T o);

    /**
     * @return количество разных элементов
     */
    int size();

    /**
     * Добавляет все элементы из source в текущий контейнер,
     * при совпадении ключей, суммирует значения
     *
     * @param source контейнер для добавления
     */
    void addAll(CountMap<T> source);

    /**
     * @return java.util.Map.
     * ключ - добавленный элемент,
     * значение - количество его добавлений
     */
    Map<T, Integer> toMap();

    /**
     * Тот же самый контракт как и toMap(), только всю информацию записывает в destination
     *
     * @param destination Map, в которую запишет всю информацию
     */
    void toMap(Map<T, Integer> destination);
}

