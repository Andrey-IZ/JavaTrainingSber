package com.sber.javaschool.hometask7;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public List<String> getPluginPaths() {
        return null;
    }

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, InstantiationException {
        var path = String.join(File.separator, pluginRootDirectory, pluginName);

        URL[] urls = new URL[]{new File(path).toURI().toURL()};
        MyClassLoader classLoader = new MyClassLoader(urls);
        try {
            return (Plugin) classLoader.loadClass(pluginClassName).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("The default constructor hasn't been found");
            throw new InstantiationException(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("The class hasn't been found");
        }
        return null;
    }
}
