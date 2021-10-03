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
    void execute_test() {
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
        ExecutionManager manager = new ExecutionManagerImpl(2);
        Context context = manager.execute(callback, tasks);
        assertTimeout(Duration.ofMillis(1000), () -> {
            while (!context.isFinished()) {
                Thread.yield();
            }
        });
        assertTrue(context.isFinished());
        assertTrue(hasCompleted.get());
        assertEquals(threadAmount, context.getCompletedTaskCount());
    }

    @Test
    void execute_failed_tasks_test() {
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
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException ignore) {
                    }
                };
            }
            taskCompleted.incrementAndGet();
        }

        ExecutionManager manager = new ExecutionManagerImpl(2);
        Context context = manager.execute(callback, tasks);
        assertTimeout(Duration.ofMillis(1000), () -> {
            while (!context.isFinished()) {
                Thread.yield();
            }
        });
        assertTrue(context.isFinished());
        assertTrue(hasCompleted.get());
        assertEquals(1, context.getFailedTaskCount());
        assertEquals(threadAmount, context.getCompletedTaskCount());
        assertEquals(threadAmount, taskCompleted.get());
    }

    @Test
    void execute_with_interruption() {

    }
}