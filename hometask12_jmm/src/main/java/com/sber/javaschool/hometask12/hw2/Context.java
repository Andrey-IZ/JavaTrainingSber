package com.sber.javaschool.hometask12.hw2;

import java.util.concurrent.Future;

public interface Context {
    /**
     * @return - количество тасков, которые на текущий момент успешно выполнились
     */
    int getCompletedTaskCount();

    /**
     * @return - количество тасков, при выполнении которых произошел Exception
     */
    int getFailedTaskCount();

    /**
     * @return - количество тасков, которые не были выполены из-за отмены (вызовом предыдущего метода)
     */
    int getInterruptedTaskCount();

    /**
     * Отменяет выполнения тасков, которые еще не начали выполняться
     */
    void interrupt();

    /**
     * @return - true, если все таски были выполнены или отменены, false в противном случае
     */
    boolean isFinished();

    /**
     * Add the running tasks
     */
    void add(Future<?> future);
}
