package com.sber.javaschool.hometask12.hw2;

public interface ExecutionManager {
    /**
     * принимает массив тасков, это задания которые ExecutionManager должен выполнять параллельно
     * (в вашей реализации пусть будет в своем пуле потоков).
     * После завершения всех тасков должен выполниться callback (ровно 1 раз)
     */
    Context execute(Runnable callback, Runnable... tasks);
}
