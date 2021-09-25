package com.sber.javaschool.hometask10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        String fileName = "text.txt";
        List<Integer> numArray = getArray(fileName);
        List<Thread> threadList = new ArrayList<>();
        int count = 0;
        for (Integer number : numArray) {
            var t = new Thread(() -> System.out.println(MessageFormat.format("{0}: {1} => {2}",
                    Thread.currentThread().getName(), number, factorial(number))));
            threadList.add(t);
            t.setName("Thread" + ++count);
            t.start();
        }
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static List<Integer> getArray(String fileName) {
        var classloader = Thread.currentThread().getContextClassLoader();
        var is = classloader.getResourceAsStream(fileName);
        List<Integer> arrayList = new ArrayList<>();
        if (is == null)
            return arrayList;
        var streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                arrayList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static BigInteger factorial(int value) {
        BigInteger result = BigInteger.valueOf(1);

        if (value == 0 || value == 1)
            return result;

        for (int i = 1; i <= value; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }
}
