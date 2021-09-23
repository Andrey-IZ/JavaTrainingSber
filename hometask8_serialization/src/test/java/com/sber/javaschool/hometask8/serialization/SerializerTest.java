package com.sber.javaschool.hometask8.serialization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializerTest {

    private ISerializer serializer;

    @BeforeEach
    void init() {
        serializer = new Serializer();
    }

    @Test
    void reload_file_test() throws IOException {
        boolean isZip = false;
        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put("key1", "value1");
        expectedMap.put("key2", "value2");
        var fileName = File.createTempFile("temp", "cache").getAbsolutePath();
        serializer.save(expectedMap, fileName, false);
        var actualMap = serializer.load(fileName, false);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void reload_zip_file_test() throws IOException {
        Map<Object, Object> expectedMap = new HashMap<>();
        expectedMap.put("key1", "value1");
        expectedMap.put("key2", "value2");
        var fileName = File.createTempFile("temp", "cache").getAbsolutePath();
        serializer.save(expectedMap, fileName, true);
        var actualMap = serializer.load(fileName, true);
        assertEquals(expectedMap, actualMap);
    }
}