package com.sber.javaschool.hometask12.hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ExecutionManagerImplTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void execute_test() throws InterruptedException {
        AtomicBoolean hasCompleted = new AtomicBoolean(false);
        AtomicInteger taskCompleted = new AtomicInteger();
        Runnable callback = () -> hasCompleted.set(true);
        int threadAmount = 10;
        Runnable[] tasks = new Runnable[threadAmount];
        Arrays.fill(tasks, (Runnable) () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ignore) {
            }
            taskCompleted.incrementAndGet();
        });
        ExecutionManager manager = new ExecutionManagerImpl(10);
        Context context = manager.execute(callback, tasks);
        assertTimeout(Duration.ofMillis(1000), () -> {
            while (!context.isFinished()) {
                Thread.yield();
            }
        });
        assertTrue(context.isFinished());
        Thread.sleep(10);
        assertTrue(hasCompleted.get());
        assertEquals(threadAmount, context.getCompletedTaskCount());
    }

    @Test
    void execute_failed_tasks_test() throws InterruptedException {
        AtomicBoolean hasCompleted = new AtomicBoolean(false);
        AtomicInteger taskCompleted = new AtomicInteger();
        Runnable callback = () -> hasCompleted.set(true);
        int threadAmount = 10;
        Runnable[] tasks = new Runnable[threadAmount];
        for (int i = 0; i < tasks.length; i++) {
            if (i == 0) {
                tasks[i] = () -> {
                    throw new IllegalArgumentException();
                };
            } else {
                tasks[i] = () -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException ignore) {
                    }
                };
            }
            taskCompleted.incrementAndGet();
        }

        ExecutionManager manager = new ExecutionManagerImpl(2);
        Context context = manager.execute(callback, tasks);
        assertTimeout(Duration.ofMillis(500 * 10 + 3000), () -> {
            while (!context.isFinished()) {
                Thread.yield();
            }
        });
        assertTrue(context.isFinished());
        Thread.sleep(10);
        assertTrue(hasCompleted.get());
        assertEquals(1, context.getFailedTaskCount());
        assertEquals(threadAmount, context.getCompletedTaskCount());
        assertEquals(threadAmount, taskCompleted.get());
    }

    @Test
    void execute_with_interruption() throws InterruptedException {
        AtomicBoolean hasCompleted = new AtomicBoolean(false);
        AtomicInteger taskCompleted = new AtomicInteger();
        Runnable callback = () -> hasCompleted.set(true);
        int threadAmount = 10;
        Runnable[] tasks = new Runnable[threadAmount];
        for (int i = 0; i < tasks.length; i++) {
            int finalI = i;
            tasks[i] = () -> {
                try {
                    var anticipatingTime = 1;
                    if (finalI % 5 == 0) anticipatingTime = 10000;
                    TimeUnit.MILLISECONDS.sleep(anticipatingTime);
                } catch (InterruptedException ignore) {
                }
            };
            taskCompleted.incrementAndGet();
        }

        ExecutionManager manager = new ExecutionManagerImpl(2);
        Context context = manager.execute(callback, tasks);
        Thread.sleep(2000);
        context.interrupt();
        assertTrue(context.isFinished());
        Thread.sleep(10);
        assertTrue(hasCompleted.get());
        assertEquals(0, context.getFailedTaskCount());
        assertTrue(context.getCompletedTaskCount() >= 2);
        assertTrue(context.getInterruptedTaskCount() >= 2);
        assertEquals(threadAmount, taskCompleted.get());
    }
}