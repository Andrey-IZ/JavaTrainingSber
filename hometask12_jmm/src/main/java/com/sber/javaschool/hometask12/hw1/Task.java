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

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public T get() throws TaskException {
        // TODO
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
