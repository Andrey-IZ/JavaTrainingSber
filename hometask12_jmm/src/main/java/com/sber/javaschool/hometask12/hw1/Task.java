package com.sber.javaschool.hometask12.hw1;

import java.util.concurrent.Callable;

/**
 * Данный класс в конструкторе принимает экземпляр java.util.concurrent.Callable.
 * Callable похож на Runnuble, но результатом его работы является объект (а не void).
 * <p>
 * Ваша задача реализовать метод get() который возвращает результат работы Callable.
 * Выполнение callable должен начинать тот поток, который первый вызвал метод get().
 * <p>
 * Если несколько потоков одновременно вызывают этот метод, то выполнение должно начаться
 * только в одном потоке, а остальные должны ожидать конца выполнения (не нагружая процессор).
 * Если при вызове get() результат уже просчитан, то он должен вернуться сразу,
 * (даже без задержек на вход в синхронизированную область).
 * <p>
 * Если при просчете результата произошел Exception, то всем потокам при вызове get(),
 * надо кидать этот Exception, обернутый в ваш RuntimeException (подходящее название своему
 * ексепшену придумайте сами).
 *
 * @param <T>
 */
public class Task<T> {
    private final Callable<? extends T> callable;
    //    private ConcurrentHashMap<Callable<? extends T>, T> cache = new ConcurrentHashMap<>();
//    private ConcurrentHashMap<Callable<? extends T>, TaskException> cacheFailedResult = new ConcurrentHashMap<>();
    private volatile T result = null;
    private volatile TaskException resultAborted = null;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws TaskException {
        if (resultAborted != null) {
            throw resultAborted;
        }
        if (result != null) {
            return result;
        }
        synchronized (this) {
            if (result == null) {
                if (resultAborted != null) {
                    throw resultAborted;
                }
                try {
                    result = callable.call();
                    resultAborted = null;
                } catch (Exception e) {
                    resultAborted = new TaskException("It's been thrown exception: " + e.getMessage());
                    result = null;
                    throw resultAborted;
                }
            }
        }
        return result;
//        if (cache.containsKey(callable)) {
//            return cache.get(callable);
//        }
//        if (cacheFailedResult.containsKey(callable)) {
//            throw cacheFailedResult.get(callable);
//        }
//        synchronized (this) {
//            if (!cache.containsKey(callable)) {
//                try {
//                    cache.put(callable, callable.call());
//                } catch (Exception e) {
//                    cacheFailedResult.put(callable, new TaskException("It's been thrown exception: " + e.getMessage()));
//                    throw cacheFailedResult.get(callable);
//                }
//            }
//        }
//        return cache.get(callable);
    }
}
