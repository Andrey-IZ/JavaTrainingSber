package com.sber.javaschool.hometask7;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PluginManager {
    private final String pluginRootDirectory;
    private Plugin[] plugins;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public List<String> getPluginPaths() {
        return null;
    }

    public void load() throws InstantiationException {
        Path[] paths;
        try {
            paths = Files.walk(Path.of(pluginRootDirectory))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".class"))
                    .map(Path::toAbsolutePath)
                    .toArray(Path[]::new);
        } catch (IOException ignored) {
            return;
        }

        List<Plugin> pluginList = new ArrayList<>();
        URL[] urls = Arrays.stream(paths)
                .map(Path::getParent)
                .distinct()
                .map(path -> {
                    try {
                        return path.toUri().toURL();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toArray(URL[]::new);

        MyClassLoader classLoader = new MyClassLoader(urls);
        for (Path path : paths) {
            var curPluginDir = path.getName(path.getNameCount() - 2).toString();
            var pluginName = MessageFormat.format("{0}.{1}", curPluginDir
                    , path.getFileName().toString().replace(".class", ""));
            pluginList.add(loadPlugin(classLoader, pluginName));
        }
        plugins = pluginList.toArray(new Plugin[0]);
    }

    private Plugin loadPlugin(MyClassLoader classLoader, String pluginName) throws InstantiationException {
        try {
            return (Plugin) classLoader.loadClass(pluginName).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("The default constructor hasn't been found");
            throw new InstantiationException(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("The class hasn't been found");
        } catch (NoClassDefFoundError error) {
            System.out.println(error.getMessage());
        }
        return null;
    }

    private Plugin loadClass(URL[] urls, String pluginClassName) throws InstantiationException {
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

    public Plugin load(String pluginName, String pluginClassName) throws MalformedURLException, InstantiationException {
        var path = String.join(File.separator, pluginRootDirectory, pluginName);

        URL[] urls = new URL[]{new File(path).toURI().toURL()};
        return loadClass(urls, pluginClassName);
    }

    public Plugin[] getPlugins() {
        return plugins;
    }
}
