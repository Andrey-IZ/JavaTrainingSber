package com.sber.javaschool.hometask14.service;

public class ServiceFactory {
    public static Service create() {
        return new ServiceImpl();
    }
}
