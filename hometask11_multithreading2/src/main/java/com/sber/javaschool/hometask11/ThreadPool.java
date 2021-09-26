package com.sber.javaschool.hometask11;

public interface ThreadPool {
    /**
     * Запускает потоки. Потоки бездействуют, до тех пор пока не появится новое задание в очереди (см. execute)
     */
    void start();

    /**
     * Складывает это задание в очередь. Освободившийся поток должен выполнить это задание. Каждое задание должны быть выполнено ровно 1 раз
     *
     * @param runnable - task
     */
    void execute(Runnable runnable);

    void join();

    void shutdown();

}
