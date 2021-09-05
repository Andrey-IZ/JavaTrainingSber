package com.sber.javaschool.hometask5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Задача 7:
 * Реализовать следующий класс по документации
 */
public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        Map<String, Method> allSetters = getAllSetters(to);
        Map<String, Method> allGetters = getAllGetters(from);

        for (Map.Entry<String, Method> entry : allGetters.entrySet()) {
            Method getMethod = entry.getValue();
            getMethod.setAccessible(true);

            if (allSetters.containsKey(getMethod.getName().substring(3))) {
                Method setMethod = allSetters.get(entry.getKey());
                setMethod.setAccessible(true);
                if (isCompareParams(getMethod, setMethod)) {
                    setMethod.invoke(to, getMethod.invoke(from));
                }
            }
        }
    }

    private static boolean isCompareParams(Method getMethod, Method setMethod) {
        return getMethod.getReturnType() == setMethod.getParameterTypes()[0]
                && getMethod.getName().substring(3).equals(setMethod.getName().substring(3));
    }

    private static Map<String, Method> getAllSetters(Object to) {
        return Arrays.stream(to.getClass().getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()) &&
                        method.getName().startsWith("set")
                        && method.getParameterCount() == 1)
                .collect(Collectors.toMap(method -> method.getName().substring(3), method -> method));
    }

    private static Map<String, Method> getAllGetters(Object from) {
        return Arrays.stream(from.getClass().getDeclaredMethods())
                .filter(method -> Modifier.isPublic(method.getModifiers()) &&
                        method.getName().startsWith("set") &&
                        method.getParameterCount() == 0
                        && method.getReturnType() != void.class)
                .collect(Collectors.toMap(method -> method.getName().substring(3), method -> method));
    }
}