package com.sber.javaschool.hometask7;

import com.sber.javaschool.hometask7.plugins.plugin.TestPlugin;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PluginManagerTest {

    @Test
    void load_same_plugin_with_another_plugin_in_dir() throws MalformedURLException, InstantiationException {
        TestPlugin expectedPlugin = new com.sber.javaschool.hometask7.plugins.plugin.TestPlugin();
        var pluginDir = Paths.get("", "target", "test-classes", expectedPlugin.getClass().getPackageName()
                        .replace('.', File.separatorChar))
                .toAbsolutePath().toString();
        PluginManager pluginManager = new PluginManager(pluginDir);
        var actualPlugin = pluginManager.load(expectedPlugin.getClass().getSimpleName(),
                expectedPlugin.getClass().getCanonicalName());

        compareByClass(actualPlugin, expectedPlugin);
        comparePluginsByOutput(actualPlugin, expectedPlugin);
    }

    private void comparePluginsByOutput(Plugin actualPlugin, Plugin expectedPlugin) {
        String expectedOutput = null, actualOutput = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {
            System.setOut(printStream);
            expectedPlugin.doUseful();
            expectedOutput = outputStream.toString().stripTrailing();
        } catch (IOException ignored) {
            fail();
        }
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8)) {
            System.setOut(printStream);
            actualPlugin.doUseful();
            actualOutput = outputStream.toString().stripTrailing();
        } catch (IOException e) {
            fail();
        }
        assertNotNull(expectedOutput);
        assertNotNull(actualOutput);
        assertEquals(expectedOutput, actualOutput);
    }

    private void compareByClass(Plugin expectedPlugin, Plugin actualPlugin) {
        assertEquals(expectedPlugin.getClass(), actualPlugin.getClass());
    }

    @Test
    void load_plugins_with_same_name() {

    }
}