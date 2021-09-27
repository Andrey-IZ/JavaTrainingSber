package com.sber.javaschool.hometask11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ScalableThreadPoolTest {
    private List<Runnable> tasks;
    private ScalableThreadPool scalableThreadPool;
    private Thread serviceThread;

    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        serviceThread = new Thread(() -> {
            while (!Thread.interrupted()) {
//                var threads = scalableThreadPool.getThreads();
//                StringBuilder sb = new StringBuilder();
//                sb.append(MessageFormat.format("ScalableThreadPool: size({0}) ", threads.size()));
//                threads.forEach(thread -> sb.append(thread.getName()).append(" = ").append(thread.getState()).append(", "));
//                System.out.println(sb);
                assertTrue(scalableThreadPool.size() <= scalableThreadPool.getMaxJobCount(), "Размер пула превысил лимит");
                assertTrue(scalableThreadPool.size() >= scalableThreadPool.getMinJobCount(), "Размер пула меньше минимального лимита");
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
            }
        });
        serviceThread.setDaemon(true);
    }

    @Test
    void min_max_jobs_test() throws InterruptedException {
        initTasks(10);
        scalableThreadPool = new ScalableThreadPool(2, 5);
        serviceThread.start();
        scalableThreadPool.start();
        tasks.forEach(runnable -> {
            scalableThreadPool.execute(runnable);
            assertTrue(scalableThreadPool.size() <= scalableThreadPool.getMaxJobCount(), "Размер пула превысил лимит");
            assertTrue(scalableThreadPool.size() >= scalableThreadPool.getMinJobCount(), "Размер пула меньше минимального лимита");
        });
        Thread.sleep(10);
        initTasks(10);
        scalableThreadPool.join();
        scalableThreadPool.shutdown();
    }

    private void initTasks(int count) {
        for (int i = 0; i < count; i++) {
//            int finalI = i;
            tasks.add(() -> {
                long timeout = (long) (Math.random() * 10);
                long t = 0;
                while (++t < 1_000_000 * timeout) {
                    Thread.yield();
                }
//                System.out.println(MessageFormat.format("Task {0} in {1}: finished",
//                        finalI + 1, Thread.currentThread().getName()));

            });
        }
    }
}