package com.sber.javaschool.hometask11;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Количество запущенных потоков может быть увеличено от минимального к максимальному,
 * если при добавлении нового задания в очередь нет свободного потока для исполнения
 * этого задания. При отсутствии задания в очереди, количество потоков опять должно
 * быть уменьшено до значения min
 */
public class ScalableThreadPool implements ThreadPool {
    private final int minJobCount;
    private final int maxJobCount;

    private final List<Thread> jobs;
    private final Queue<Runnable> tasksQueue = new ConcurrentLinkedQueue<>();

    private volatile boolean isRunning = false;

    public ScalableThreadPool(int minJobCount, int maxJobCount) {
        this.minJobCount = minJobCount;
        this.maxJobCount = maxJobCount;
        jobs = new ArrayList<>();
        for (int i = 0; i < minJobCount; i++) {
            jobs.add(new Job());
        }
    }

    @Override
    public void start() {
        jobs.parallelStream().forEach(Thread::start);
    }

    @Override
    public void execute(Runnable task) {
        tasksQueue.add(task);

        expandPool();

        isRunning = true;
        synchronized (tasksQueue) {
            tasksQueue.notifyAll();
        }
    }

    private void expandPool() {
        if (tasksQueue.size() > jobs.size() && jobs.size() < maxJobCount) {
            Job newJob = new Job();
            jobs.add(newJob);
            newJob.start();
        }
    }

    private void squeezePool() {
        if (tasksQueue.size() < jobs.size() && tasksQueue.size() >= minJobCount) {
            jobs.parallelStream()
                    .filter(job -> Thread.State.RUNNABLE.equals(job.getState()))
                    .findFirst()
                    .ifPresent(thread -> {
                        thread.interrupt();
                        jobs.remove(thread);
                    });
        }
    }

    public int size() {
        return jobs.size();
    }

    @Override
    public void join() {
        while (!tasksQueue.isEmpty()) {
            Thread.yield();
        }
    }

    @Override
    public void shutdown() {
        jobs.forEach(Thread::interrupt);
    }

    public List<Thread> getThreads() {
        return jobs;
    }

    private class Job extends Thread {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                squeezePool();
                while (!isRunning) {
                    synchronized (tasksQueue) {
                        try {
                            tasksQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                try {
                    Runnable task = tasksQueue.poll();

                    if (task == null) {
                        synchronized (tasksQueue) {
                            isRunning = false;
                            tasksQueue.wait();
                        }
                    } else {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }


        }
    }

    public int getMinJobCount() {
        return minJobCount;
    }

    public int getMaxJobCount() {
        return maxJobCount;
    }
}
