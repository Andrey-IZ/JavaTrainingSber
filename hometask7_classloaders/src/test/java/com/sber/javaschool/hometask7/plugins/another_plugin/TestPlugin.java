package com.sber.javaschool.hometask7.plugins.another_plugin;

import com.sber.javaschool.hometask7.Plugin;

public class TestPlugin implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("I'm advanced plugin");
    }

    @Override
    public int hashCode() {
        return 0x1;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
