package com.sber.javaschool.hometask12.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void get() throws Exception {
        var resultsList = new ConcurrentLinkedQueue<Double>();
        var atomicInteger = new AtomicInteger();
        Task<Double> task = new Task<>(() -> {
            TimeUnit.MILLISECONDS.sleep(1);
            atomicInteger.incrementAndGet();
            return 33.0;
        });
        List<Thread> threadList = new ArrayList<>();
        int amountThreads = 100;

        for (int i = 0; i < amountThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    var result = task.get();
                    assertNotNull(result);
                    resultsList.add(result);
                } catch (TaskException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.join();
        }
        assertEquals(amountThreads, (long) resultsList.size());
        assertEquals(1, atomicInteger.get());
        var element = resultsList.element();
        assertTrue(resultsList.stream().allMatch(element::equals));
    }

    @Test
    void testWIthException() throws InterruptedException {
        ConcurrentLinkedQueue<Throwable> thrownCauseList = new ConcurrentLinkedQueue<>();
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            if (throwable instanceof TaskException) {
                thrownCauseList.add(throwable);
            }
        });

        Task<Double> task = new Task<>(() -> {
            throw new TaskException("has happened the problem");
        });
        List<Thread> threadList = new ArrayList<>();
        int amountThreads = 100;

        for (int i = 0; i < amountThreads; i++) {
            Thread thread = new Thread(task::get);
            thread.start();
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.join();
        }
        assertEquals(amountThreads, thrownCauseList.size(), "There mustn't be any assertion errors");
        var firstElem = thrownCauseList.peek();
        assertTrue(thrownCauseList.stream().allMatch(throwable -> throwable.equals(firstElem)), "size: " + thrownCauseList);
    }
}