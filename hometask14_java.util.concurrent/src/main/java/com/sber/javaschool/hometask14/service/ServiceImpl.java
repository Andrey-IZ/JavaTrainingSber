package com.sber.javaschool.hometask14.service;

public class ServiceImpl implements Service {
    @Override
    public double doHardWorkInMemory(String name, Integer value) {
        System.out.println("doHardWorkInMemory");
        return value;
    }

    @Override
    public double doHardWorkFile(String name, Integer value) {
        return value;
    }

    @Override
    public double doHardWorkZip(String name, Integer value) {
        return value;
    }
}
