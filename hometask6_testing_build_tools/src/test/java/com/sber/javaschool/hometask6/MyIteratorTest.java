package com.sber.javaschool.hometask6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyIteratorTest {
    private MyIterator<String> myIterator;

    @BeforeEach
    void setUp() {
        myIterator = new MyIterator<>(Arrays.asList("1", "2", "3"));
    }

    @Test
    void hasNext() {
        assertTrue(myIterator.hasNext());
        assertEquals("1", myIterator.next());
        assertTrue(myIterator.hasNext());
        assertEquals("2", myIterator.next());
        assertTrue(myIterator.hasNext());
        assertEquals("3", myIterator.next());
        assertFalse(myIterator.hasNext());
    }

    @Test
    void next() {
        assertEquals("1", myIterator.next());
        assertEquals("2", myIterator.next());
        assertEquals("3", myIterator.next());
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.next());
    }

    @Test
    void hasPrevious() {
        for (int i = 0; i < 3; i++) {
            assertEquals(String.valueOf(i + 1), myIterator.next());
        }
        assertTrue(myIterator.hasPrevious());
        assertEquals("2", myIterator.previous());
        assertTrue(myIterator.hasPrevious());
        assertEquals("1", myIterator.previous());
        assertFalse(myIterator.hasPrevious());
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.previous());
    }

    @Test
    void previous() {
        myIterator.toBack();
        assertEquals("3", myIterator.previous());
        assertEquals("2", myIterator.previous());
        assertEquals("1", myIterator.previous());
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.previous());
    }

    @Test
    void nextIndex() {
        assertEquals(0, myIterator.nextIndex());
        myIterator.next();
        assertEquals(1, myIterator.nextIndex());
        myIterator.next();
        assertEquals(2, myIterator.nextIndex());
        myIterator.next();
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.nextIndex());
    }

    @Test
    void previousIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.previousIndex());
        myIterator.next();
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.previousIndex());
        myIterator.next();
        assertEquals(0, myIterator.previousIndex());
        myIterator.next();
        assertEquals(1, myIterator.previousIndex());
    }

    @Test
    void remove() {
        assertTrue(myIterator.hasNext());
        assertThrows(IllegalStateException.class, () -> myIterator.remove());
        assertTrue(myIterator.hasNext());
        assertFalse(myIterator.hasPrevious());
        assertEquals("1", myIterator.next());

        myIterator.remove();
        assertTrue(myIterator.hasNext());
        assertFalse(myIterator.hasPrevious());
        assertEquals("2", myIterator.next());

        myIterator.remove();
        assertTrue(myIterator.hasNext());
        assertFalse(myIterator.hasPrevious());
        assertEquals("3", myIterator.next());

        myIterator.remove();
        assertFalse(myIterator.hasNext());
        assertFalse(myIterator.hasPrevious());
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.next());

        assertThrows(IllegalStateException.class, () -> myIterator.remove());
        try {
            Field field = myIterator.getClass().getDeclaredField("list");
            field.setAccessible(true);
            int size = ((List<?>) field.get(myIterator)).size();
            assertEquals(0, size);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void set() {
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.set("0"));
        assertEquals("1", myIterator.next());
        myIterator.set("w");
        assertEquals("2", myIterator.next());
        assertEquals("w", myIterator.previous());
        myIterator.set("Q");
        assertEquals("2", myIterator.next());
        assertEquals("Q", myIterator.previous());

        assertEquals("2", myIterator.next());
        assertEquals("3", myIterator.next());
        myIterator.set("Z");
        assertEquals("2", myIterator.previous());
        assertEquals("Z", myIterator.next());

        try {
            Field field = myIterator.getClass().getDeclaredField("list");
            field.setAccessible(true);
            int size = ((List<?>) field.get(myIterator)).size();
            assertEquals(3, size);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void add() {
        myIterator.add("4");
        assertEquals("1", myIterator.next());
        assertEquals("2", myIterator.next());
        assertEquals("3", myIterator.next());
        assertEquals("4", myIterator.next());
        assertThrows(IndexOutOfBoundsException.class, () -> myIterator.next());

        myIterator.add("5");
        assertEquals("5", myIterator.next());
    }

    @Test
    void toBack() {
        myIterator.toBack();
        assertTrue(myIterator.hasPrevious());
        assertEquals("3", myIterator.previous());
        assertTrue(myIterator.hasPrevious());
        assertEquals("2", myIterator.previous());
        assertTrue(myIterator.hasPrevious());
        assertEquals("1", myIterator.previous());
        assertFalse(myIterator.hasPrevious());
    }

    @Test
    void toFront() {
        while (myIterator.hasNext()) {
            myIterator.next();
        }
        myIterator.toFront();
        assertTrue(myIterator.hasNext());
        assertEquals("1", myIterator.next());
    }
}