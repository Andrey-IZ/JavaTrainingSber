package com.sber.javaschool.hometask1;

import com.sber.javaschool.hometask1.array_utilities.ArrayManager;
import com.sber.javaschool.hometask1.converters.Temperatures;
import com.sber.javaschool.hometask1.hierarchy.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        task1_algorithms(1, 100, 20);
        task2_canvas();
        task3_temperature_converters();
    }

    /**
     * Задача 1:
     * Написать сортировку пузырьком или бинарный поиск элемента в массиве.
     *
     * @param from  начальный размер массива
     * @param to    конечный размер массива
     * @param count количество раундов тестового прогона
     */
    private static void task1_algorithms(int from, int to, int count) {
        var list = new ArrayList<Integer>(count);
        for (int i = from; i <= to; i++) list.add(i);
        Collections.shuffle(list);
        int[] array = list.stream().mapToInt(integer -> integer).toArray();

        System.out.println("--- Сортировка пузырьком --- ");
        System.out.println("Исходный массив: " + list);
        ArrayManager.bubbleSort(array);
        System.out.printf("Отсортированный массив %s%n", Arrays.toString(array));

        Random rand = new Random();
        System.out.println(" --- Бинарный поиск ---");
        for (int round = 0; round < 5; round++) {
            System.out.println("Попытка: " + (round + 1));
            int index = rand.nextInt(to) + from;
            long startTime = System.nanoTime();
            int result = ArrayManager.binarySearch(array, array[index]);
            long stopTime = System.nanoTime();
            if (result < 0 || index != result) {
                System.out.println("Элемент не найден");
            } else {
                System.out.printf("Элемент %d найден по индексу %d за время %s мкс. %n", array[index], result, stopTime - startTime);
            }
        }
    }

    /**
     * Задача 2.
     * Реализовать иерархию объектов Circle, Rect, Triangle, Square
     */
    private static void task2_canvas() {
        System.out.println("--- Plotting shapes ---");
        Shape[] shapes = {
                new Circle(50, 60, 10),
                new Rect(45, 73, 45, 66),
                new Rect(44, 74, 14, 51),
                new Square(11, 6, 42),
                new Triangle(15, 61, 42, 33, 20),
                new Triangle(10, 59, 9, 11, 6)
        };
        Random random = new Random();
        for (Shape shape : shapes) {
            shape.draw();
            shape.resize(random.nextDouble() * 10);
            shape.move(random.nextInt() * 10, random.nextInt() * 10);
            shape.draw();
        }
    }

    /**
     * Задача 3.
     * Реализовать конвертеры температуры. Считаем, что значения будут поступать по шкале Цельсия,
     * конвертеры должны преобразовывать значение в свою шкалу.
     */
    private static void task3_temperature_converters() {
        System.out.println("--- Temperature Conversion ---");
        try {
            Random r = new Random();
            double t = r.nextDouble() * 22;
            System.out.printf("цельсий в фаренгейт: %f => %f\n", t,
                    Temperatures.CELSIUS.convert(t, Temperatures.FAHRENHEIT));
            t = r.nextDouble() * 5600;
            System.out.printf("фаренгейт в кельвин: %f => %f\n", t,
                    Temperatures.FAHRENHEIT.convert(t, Temperatures.KELVIN));
            t = r.nextDouble() * -156;
            System.out.printf("цельсий в кельвин: %f => %f\n", t,
                    Temperatures.CELSIUS.convert(t, Temperatures.KELVIN));
            t = r.nextDouble() * -123;
            System.out.printf("кельвин в фаренгейт: %f => %f\n", t,
                    Temperatures.KELVIN.convert(t, Temperatures.FAHRENHEIT));
        } catch (Temperatures.BelowAbsoluteZeroException e) {
            System.out.println(e.getMessage());
        }
    }
}
