package com.sber.javaschool.hometask7;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileEncoderTest {

    @Test
    void decodeFile() {
        String string = "test file just for example";
        byte[] data = string.getBytes(StandardCharsets.UTF_8);
        String key = "key";
        assertEquals(FileEncoder.decode(FileEncoder.encode(data, key), key), data);
    }
}