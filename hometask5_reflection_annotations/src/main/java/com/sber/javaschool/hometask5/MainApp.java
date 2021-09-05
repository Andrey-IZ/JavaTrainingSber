package com.sber.javaschool.hometask5;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        getAllMethods(MyClassChild.class);
        getAllGetters(MyClassChild.class);
        getAllSetters(MyClassChild.class);
        checkConstant(new MyClass(13));
    }

    /**
     * Задача 2:
     * Вывести на консоль все методы класса, включая все родительские методы
     * (включая приватные)
     */
    public static void getAllMethods(Class<?> clazz) {
        System.out.println("---Задача 2: все методы класса, включая все родительские методы-----");
        while (clazz != null) {
            System.out.format("- name: %s \n", clazz.getSimpleName());
            Arrays.stream(clazz.getDeclaredMethods()).map(Method::toString).forEach(System.out::println);
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * Задача 3:
     * Вывести все геттеры класса
     */
    public static void getAllGetters(Class<?> clazz) {
        System.out.println("---Задача 3: Вывести все геттеры класса--");
        System.out.println("- using java.beans:");
        try {
            var propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            Arrays.stream(propertyDescriptors)
                    .filter(ps -> ps.toString().contains(clazz.getName()))
                    .map(PropertyDescriptor::getReadMethod)
                    .forEach(System.out::println);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        System.out.println("- using java.lang.reflect:");
        Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()) &&
                        method.getName().startsWith("get") &&
                        method.getParameterCount() == 0 &&
                        !method.getReturnType().equals(void.class))
                .forEach(System.out::println);
    }

    public static void getAllSetters(Class<?> clazz) {
        System.out.println("--Вывести все сеттеры класса--");
        Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()) &&
                        method.getName().startsWith("set") &&
                        method.getParameterCount() == 1)
                .forEach(System.out::println);
    }

    /**
     * Задача 4:
     * Проверить что все String константы имеют значение = их имени
     * public static final String MONDAY = "MONDAY";
     */
    public static void checkConstant(Object obj) {
        Objects.requireNonNull(obj);

        Class<?> clazz = obj.getClass();
        var badConstantList = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> String.class.isAssignableFrom(field.getType()) &&
//                        Modifier.isPublic(field.getModifiers()) &&
                        Modifier.isStatic(field.getModifiers()) &&
                        Modifier.isFinal(field.getModifiers()))
                .filter(field -> {
                    try {
                        field.setAccessible(true);
                        return !field.getName().equals(field.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toList());
        System.out.println("все String константы имеют значение = их имени: " + badConstantList.isEmpty());
        if (!badConstantList.isEmpty()) {
            System.out.println("Константы, которые не соответствуют условию");
            badConstantList.forEach(System.out::println);
        }
    }
}
