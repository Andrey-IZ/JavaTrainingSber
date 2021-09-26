package com.sber.javaschool.hometask11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

class FixedThreadPoolTest {
    private List<Runnable> tasks;
    private FixedThreadPool fixedThreadPool;
    private Thread serviceThread;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        serviceThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                var threads = fixedThreadPool.getThreads();
                StringBuilder sb = new StringBuilder();
                sb.append("FixedThreadPool: size(").append(threads.size()).append("): ");
                threads.forEach(thread -> sb.append(thread.getName()).append(" = ").append(thread.getState()).append(", "));
                System.out.println(sb);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        serviceThread.setDaemon(true);
    }

    @Test
    void start() {
        initTasks(10);
        fixedThreadPool = new FixedThreadPool(3);
        serviceThread.start();
        fixedThreadPool.start();
        tasks.forEach(fixedThreadPool::execute);
        fixedThreadPool.join();
        fixedThreadPool.shutdown();
    }

    private void initTasks(int count) {
        for (int i = 0; i < count; i++) {
            int finalI = i;
            tasks.add(() -> {
                long timeout = (long) (Math.random() * 10);
                long t = 0;
                while (++t < 1_000_000 * timeout) {
                    Thread.yield();
                }
                System.out.println(MessageFormat.format("Task {0} in {1}: finished",
                        finalI + 1, Thread.currentThread().getName()));

            });
        }
    }
}