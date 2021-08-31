package com.sber.javaschool.hometask3.pecs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Параметризовать методы, используя правило PECS, и реализовать их
 */
public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<? super T> limit(List<? extends T> source, int size) {
//        return source.stream().limit(size).collect(Collectors.toList());
        var list = newArrayList();
        AtomicInteger limitSize = new AtomicInteger(size);
        source.forEach(t -> {
            if (limitSize.getAndDecrement() > 0) {
                list.add(t);
            }
        });
        return list;
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    /**
     * @return true, если первый лист содержит все элементы второго
     */
    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    /**
     * @return /true, если первый лист содержит хотя-бы 1 второго
     */
    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
//        return Collections.disjoint(c1, c2);
        for (T element : c1) {
            if (c2.contains(element))
                return true;
        }
        return false;
    }

    /**
     * @return лист, содержащий элементы из входного листа в диапазоне от min до max.
     * Элементы сравнивать через Comparable.
     * @implNote range(Arrays.asList ( 8, 1, 3, 5, 6, 4), 3, 6) вернет {3,4,5,6}
     */
    public static <T extends Comparable<? super T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> rangeList = newArrayList();
        if (max.compareTo(min) < 0 || list == null) return rangeList;
        for (T t : list) {
            if (t.compareTo(min) >= 0 && t.compareTo(max) <= 0) {
                rangeList.add(t);
            }
        }

        return rangeList;
    }

    /**
     * @return лист, содержащий элементы из входного листа в диапазоне от min до max.
     * Элементы сравнивать через Comparable.
     * @implNote range(Arrays.asList ( 8, 1, 3, 5, 6, 4), 3, 6) вернет {3,4,5,6}
     */
    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> c) {
        List<T> rangeList = newArrayList();
        if (c.compare(max, min) < 0 || list == null) return rangeList;
        for (T t : list) {
            if (c.compare(t, min) >= 0 && c.compare(t, max) <= 0) {
                rangeList.add(t);
            }
        }
        return rangeList;
    }
}