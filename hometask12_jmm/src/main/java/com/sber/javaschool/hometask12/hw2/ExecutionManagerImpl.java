package com.sber.javaschool.hometask12.hw2;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutionManagerImpl implements ExecutionManager {
    private ExecutorService pool;
    private volatile ContextImpl context;
    private final int threadCount;

    public ExecutionManagerImpl(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        pool = Executors.newFixedThreadPool(threadCount);
        context = new ContextImpl();

        Arrays.stream(tasks).map(task -> (Runnable) () -> {
            try {
                task.run();
            } catch (Exception e) {
                context.setFailedTaskCount();
            } finally {
                context.setCompletedTaskCount();
            }
        }).map(pool::submit).forEach(context::add);

        Runnable runnable = () -> {
            // Wait for everything to finish.
            while (true) {
                if (context.isInterrupted() || context.isFinished()) {
                    callback.run();
                    pool.shutdown();
                    break;
                }
            }
        };
        new Thread(runnable).start();
        return context;
    }
}
