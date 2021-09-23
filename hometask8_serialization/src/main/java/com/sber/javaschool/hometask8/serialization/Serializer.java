package com.sber.javaschool.hometask8.serialization;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Serializer implements ISerializer {
    @Override
    public void save(Object object, String filename, boolean isZip) {
        File file = new File(filename);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.out.println("Failed to save data");
        }

        if (isZip) {
            ZipEntry zipEntry = new ZipEntry(filename);
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(filename + ".zip"));
                 FileInputStream fileInputStream = new FileInputStream(file)) {
                zipOutputStream.putNextEntry(zipEntry);
                byte[] buffer = new byte[fileInputStream.available()];
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                System.out.println("Failed to save data");
            }
        }
    }

    @Override
    public Map<Object, Object> load(String filename, boolean isZip) {
        Map<Object, Object> map = null;

        if (isZip) {
            try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filename))) {

                while (zipInputStream.getNextEntry() != null) {
                    FileOutputStream fileOutputStream = new FileOutputStream(filename);
                    int c;
                    while ((c = zipInputStream.read()) != -1) {
                        fileOutputStream.write(c);
                    }

                    zipInputStream.closeEntry();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
        }

        try {
            map = (Map<Object, Object>) new ObjectInputStream(new FileInputStream(filename)).readObject();
        } catch (IOException e) {
            map = new HashMap<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }
}
