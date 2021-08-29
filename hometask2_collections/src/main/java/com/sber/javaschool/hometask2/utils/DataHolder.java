package com.sber.javaschool.hometask2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class DataHolder {

    public static String[] splitByWords(String text) {
        return text.toLowerCase(Locale.ROOT).split("\\W+");
    }

    public static Collection<String> loadFromFile(String fileName) throws IOException {
        var classloader = Thread.currentThread().getContextClassLoader();
        var is = classloader.getResourceAsStream(fileName);
        List<String> lines = new ArrayList<>();
        if (is == null)
            return lines;
        var streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        for (String line; (line = reader.readLine()) != null; ) {
            lines.add(line);
        }
        return lines;
//
//        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//            return stream.collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new FileNotFoundException(e.getMessage());
//        }
    }
}
