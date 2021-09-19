package com.sber.javaschool.hometask7.plugins.plugin;

import com.sber.javaschool.hometask7.Plugin;

public class AnotherPluginImpl implements Plugin {
    @Override
    public void doUseful() {
        System.out.println("I'm another plugin");
    }

    @Override
    public int hashCode() {
        return 0x3;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
