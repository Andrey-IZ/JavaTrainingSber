package com.sber.javaschool.hometask13;

import java.util.HashMap;

/**
 * Сделать цикл на 100 000 итераций, в цикле в предварительно созданную Map<Integer, String>
 * сложить ключ - индекс, значение - "value" + индекс
 * Запустить с опцией -XX:+PrintCompilation, проанализировать информацию в консоли
 * Запустить с опцией -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining,
 * проанализировать информацию в консоли
 */
public class MainTestJIT {
    public static void main(String[] args) {
        var map = new HashMap<Integer, String>();
        for (int i = 0; i < 100_000; i++) {
            map.put(i, String.valueOf(i));
        }
    }
}
