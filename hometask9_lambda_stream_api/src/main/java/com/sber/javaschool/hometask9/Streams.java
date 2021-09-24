package com.sber.javaschool.hometask9;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    private final Collection<T> collection;

    public Streams(Collection<? extends T> collection) {
        this.collection = new ArrayList<>(collection);
    }

    public static <T> Streams<T> of(Collection<T> values) {
        return new Streams<>(values);
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        collection.removeIf(predicate.negate());
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyFunction,
                                  Function<? super T, ? extends V> valueFunction) {

        Map<K, V> map = new HashMap<>();
        for (T element : collection) {
            map.put(keyFunction.apply(element), valueFunction.apply(element));
        }
        return map;
    }

    public Streams<T> transform(Function<? super T, ? extends T> function) {
        List<T> tempList = new ArrayList<>();
        for (T t : collection) {
            tempList.add(function.apply(t));
        }
        collection.clear();
        collection.addAll(tempList);
        return this;
    }

    public List<T> toList() {
        return new ArrayList<>(collection);
    }

    public void forEach(Consumer<? super T> action) {
        for (T t : collection) {
            action.accept(t);
        }
    }
}
