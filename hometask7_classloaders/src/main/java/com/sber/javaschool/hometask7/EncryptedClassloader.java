package com.sber.javaschool.hometask7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EncryptedClassloader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassloader(ClassLoader parent, String key, File dir) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classInBytes = getClassInBytes(name);
            return defineClass(name, classInBytes, 0, classInBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

    private byte[] getClassInBytes(String name) throws IOException {
        Path path = Path.of(dir.getPath(), name + ".class");
        return FileEncoder.decode(Files.readAllBytes(path), key);
    }
}
