package com.sber.javaschool.hometask7;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        URL[] urls = new URL[]{new URL(String.join(File.pathSeparator, pluginRootDirectory, pluginName))};
        MyClassLoader classLoader = new MyClassLoader(urls);
        return (Plugin) classLoader.loadClass(pluginClassName).getDeclaredConstructor().newInstance();
    }
}
