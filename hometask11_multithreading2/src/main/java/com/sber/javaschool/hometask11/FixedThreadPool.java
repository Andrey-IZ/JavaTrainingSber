package com.sber.javaschool.hometask11;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Количество потоков задается в конструкторе и не меняется.
 */
public class FixedThreadPool implements ThreadPool {

    private final Queue<Runnable> taskQueue = new ConcurrentLinkedDeque<>();
    private final List<Thread> jobs;
    private volatile boolean isRunning = false;

    public FixedThreadPool(int threadCount) {
        jobs = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            jobs.add(new Job());
        }
    }

    @Override
    public void start() {
        jobs.parallelStream().forEach(Thread::start);
    }

    @Override
    public void execute(Runnable task) {
        isRunning = true;
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notifyAll();
        }
    }

    public List<Thread> getThreads() {
        return jobs;
    }

    @Override
    public void shutdown() {
        jobs.forEach(Thread::interrupt);
    }

    @Override
    public void join() {
        while (!taskQueue.isEmpty()) {
            Thread.yield();
        }
    }

    private final class Job extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                while (!isRunning) {
                    synchronized (taskQueue) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + "has been interrupted");
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                Runnable task = taskQueue.poll();
                try {
                    if (task == null) {
                        synchronized (taskQueue) {
                            taskQueue.wait();
                            isRunning = false;
                        }
                    } else {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "has been interrupted");
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
