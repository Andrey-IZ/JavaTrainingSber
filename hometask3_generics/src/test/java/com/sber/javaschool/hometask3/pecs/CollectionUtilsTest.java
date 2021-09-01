package com.sber.javaschool.hometask3.pecs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

    List<Integer> integerList = new ArrayList<>();
    static final int LIST_LIMIT = 10;

    @BeforeEach
    void setUp() {
        Random random = new Random();
        IntStream.range(0, LIST_LIMIT).forEach(i -> integerList.add(random.nextInt(10)));
    }

    @Test
    void addAll() {
        var list = new ArrayList<>();
        CollectionUtils.addAll(integerList, list);
        assertEquals(list, integerList);

        var random = new Random();
        var doubleList = new ArrayList<Double>();
        IntStream.range(0, LIST_LIMIT).mapToDouble(i -> random.nextDouble() * 10).forEach(doubleList::add);

        CollectionUtils.addAll(doubleList, list);
        List<List<Number>> testList = new ArrayList<>();
        testList.add(new ArrayList<>(integerList));
        assertNotEquals(list, testList.stream().flatMap(List::stream).collect(Collectors.toList()));

        testList.add(new ArrayList<>(doubleList));
        assertEquals(list, testList.stream().flatMap(List::stream).collect(Collectors.toList()));
    }

    @Test
    void newArrayList() {
        List<String> stringList = CollectionUtils.newArrayList();
        assertNotNull(stringList);
        stringList.add("string");
        assertEquals(stringList.get(0), "string");

        List<Integer> integerList = CollectionUtils.newArrayList();
        assertNotNull(integerList);

        integerList.addAll(Arrays.asList(1, 2, 3));
        assertEquals(integerList, Arrays.asList(1, 2, 3));
    }

    @Test
    void indexOf() {
        Random random = new Random();
        int index = random.nextInt(LIST_LIMIT);
        var element = integerList.get(index);
        int actualIndex = CollectionUtils.indexOf(integerList, element);
        assertEquals(integerList.indexOf(element), actualIndex);
    }

    @Test
    void limit() {
        var actualList = CollectionUtils.limit(integerList, 0);
        assertEquals(actualList.size(), 0);

        int limit = new Random().nextInt((int) (LIST_LIMIT * 0.8));
        actualList = CollectionUtils.limit(integerList, limit);
        assertEquals(actualList.size(), limit);
    }

    @Test
    void add() {
        Random random = new Random();
        Integer elemInt = random.nextInt();
        int expectedSize = integerList.size() + 1;
        CollectionUtils.add(integerList, elemInt);

        assertEquals(expectedSize, integerList.size());
        assertEquals(elemInt, integerList.get(integerList.size() - 1));
    }

    @Test
    void removeAll() {
        Random random = new Random();
        random.nextBoolean();
        var testList = getTestListFiltered(random);
        IntStream.range(0, 5).forEach(value -> {
            random.setSeed(value);
            testList.set(random.nextInt(testList.size() - 1), random.nextInt());
        });
        CollectionUtils.removeAll(integerList, testList);
        long actual = integerList.stream().filter(testList::contains).count();
        assertEquals(0, actual);
    }

    private List<Integer> getTestListFiltered(Random random) {
        var list = integerList.stream().filter(i -> random.nextBoolean()).collect(Collectors.toList());
        if (list.isEmpty()) {
            getTestListFiltered(random);
        }
        return list;
    }

    @Test
    void containsAll() {
        var actual = CollectionUtils.containsAll(integerList, List.of());
        assertTrue(actual);

        var testList = getTestListFiltered(new Random());
        actual = CollectionUtils.containsAll(integerList, testList);
        assertEquals(integerList.containsAll(testList), actual);
    }

    @Test
    void containsAny() {
        var list = getTestListFiltered(new Random());
        var actual = CollectionUtils.containsAny(integerList, list);
        var expected = integerList.stream().findAny().isPresent();
        assertEquals(expected, actual);
    }

    @Test
    void range() {
        var actual = CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6);
        var expected = List.of(3, 4, 5, 6);
        assertEquals(expected, actual);
    }

    @Test
    void rangeWithComparator() {
        var actual = CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6, Comparator.naturalOrder());
        var expected = List.of(3, 4, 5, 6);
        assertEquals(expected, actual);
    }
}