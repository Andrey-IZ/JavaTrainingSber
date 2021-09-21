package com.sber.javaschool.hometask7;

import java.nio.charset.StandardCharsets;

public class FileEncoder {
    public static byte[] decode(byte[] bytes, String key) {
        byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ byteKey[i & (byteKey.length - 1)]);
        }
        return bytes;
    }

    public static byte[] encode(byte[] bytes, String key) {
        byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ byteKey[i % (byteKey.length - 1)]);
        }
        return bytes;
    }
}
