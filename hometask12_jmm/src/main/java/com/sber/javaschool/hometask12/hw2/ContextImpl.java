package com.sber.javaschool.hometask12.hw2;

import java.util.List;
import java.util.concurrent.Future;

public class ContextImpl implements Context {

    private volatile int completedTaskCount;
    private volatile int failedTaskCount;
    private volatile boolean isInterrupted;
    private List<? extends Future<?>> futures;

    public ContextImpl(List<? extends Future<?>> futures) {
        this.futures = futures;
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
        if (isInterrupted()) {
            return futures.size() - getCompletedTaskCount() - getFailedTaskCount();
        }
        return 0;
    }

    @Override
    public void interrupt() {
        setInterrupted(true);
        futures.forEach(future -> future.cancel(false));
    }

    @Override
    public boolean isFinished() {
        return getCompletedTaskCount() == futures.size()
                || getInterruptedTaskCount() == futures.size();
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }

    public void setInterrupted(boolean interrupted) {
        isInterrupted = interrupted;
    }
}
