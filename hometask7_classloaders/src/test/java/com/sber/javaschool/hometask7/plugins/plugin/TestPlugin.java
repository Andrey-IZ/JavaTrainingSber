package com.sber.javaschool.hometask7.plugins.plugin;

import com.sber.javaschool.hometask7.Plugin;

public class TestPlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("I'm the first plugin");
    }

    @Override
    public int hashCode() {
        return 0x2;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
