package com.sber.javaschool.hometask12.hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class ContextImpl implements Context {

    private volatile int completedTaskCount;
    private volatile int failedTaskCount;
    private volatile int interruptedCount;
    private volatile boolean isInterrupted;
    private final List<Future<?>> futures = new ArrayList<>();

    public ContextImpl() {
    }

    public synchronized void setCompletedTaskCount() {
        completedTaskCount++;
    }

    public synchronized void setFailedTaskCount() {
        failedTaskCount++;
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount;
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount;
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedCount;
    }

    @Override
    public synchronized void interrupt() {
        setInterrupted(true);
        futures.forEach(future -> future.cancel(false));
        interruptedCount = (int) futures.stream().filter(Future::isCancelled).count();
    }

    @Override
    public boolean isFinished() {
        return getCompletedTaskCount() + getInterruptedTaskCount() == futures.size();
    }

    @Override
    public void add(Future<?> future) {
        futures.add(future);
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }

    public void setInterrupted(boolean interrupted) {
        isInterrupted = interrupted;
    }
}
