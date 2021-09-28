package com.sber.javaschool.hometask12.hw1;

import java.util.concurrent.Callable;

public class Task<T> {
    private final Callable<? extends T> callable;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws Exception {
        // TODO
        return callable.call();
    }
}
