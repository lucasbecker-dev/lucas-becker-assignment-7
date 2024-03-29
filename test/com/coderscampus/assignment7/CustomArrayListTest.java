package com.coderscampus.assignment7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {
    final private int INITIAL_CAPACITY = 20;
    private CustomArrayList<Integer> listDefaultCapacity;
    private CustomArrayList<Integer> listCustomCapacity;

    @BeforeEach
    void setUp() {
        listDefaultCapacity = new CustomArrayList<>();
        listCustomCapacity = new CustomArrayList<>(INITIAL_CAPACITY);
    }

    @Test
    void testConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new CustomArrayList<Integer>(-1));
    }

    @Test
    void testToString() {
        System.out.println(listDefaultCapacity);
        System.out.println(listCustomCapacity);
        assertEquals(
                "CustomArrayList{capacity=10, " +
                        "nextIndex=0, " +
                        "size=0, " +
                        "items=[null, null, null, null, null, null, null, null, null, null]}",
                listDefaultCapacity.toString());
        assertEquals(
                "CustomArrayList{capacity=" + INITIAL_CAPACITY +
                        ", nextIndex=0, " +
                        "size=0, " +
                        "items=[null, null, null, null, null, null, null, null, null, null, " +
                        "null, null, null, null, null, null, null, null, null, null]}",
                listCustomCapacity.toString());
    }

    @Test
    void testGetCapacity() {
        assertEquals(10, listDefaultCapacity.getCapacity());
        assertEquals(INITIAL_CAPACITY, listCustomCapacity.getCapacity());
    }

    @Test
    void testCapacityIncrease() {
        for (int i=0; i<100; i++) {
            listDefaultCapacity.add(i);
            listCustomCapacity.add(i);
        }
        assertEquals(160, listDefaultCapacity.getCapacity());
        assertEquals(160, listCustomCapacity.getCapacity());
    }

    @Test
    void testCapacityDecrease() {
        for (int i=0; i<100; i++) {
            listDefaultCapacity.add(i);
            listCustomCapacity.add(i);
        }
        for (int i=99; i>=0; i--) {
            listDefaultCapacity.remove(i);
            listCustomCapacity.remove(i);
        }
        assertEquals(0, listDefaultCapacity.getCapacity());
        assertEquals(0, listCustomCapacity.getCapacity());
    }

    @Test
    void testIsEmpty() {
        assertTrue(listDefaultCapacity.isEmpty());
        assertTrue(listCustomCapacity.isEmpty());

        listDefaultCapacity.add(1);
        listCustomCapacity.add(2);

        assertFalse(listDefaultCapacity.isEmpty());
        assertFalse(listCustomCapacity.isEmpty());
    }

    @Test
    void testAddAndGet() {
        listDefaultCapacity.add(1);
        listCustomCapacity.add(2);
        assertEquals(1, listDefaultCapacity.get(0));
        assertEquals(2, listCustomCapacity.get(0));
    }

    @Test
    void testAddSpecificIndex() {
        listDefaultCapacity.add(0, 1);
        listCustomCapacity.add(0, 1);
        assertEquals(1, listDefaultCapacity.get(0));
        assertEquals(1, listDefaultCapacity.get(0));

        listDefaultCapacity.add(0, 2);
        listCustomCapacity.add(0, 2);
        assertEquals(2, listCustomCapacity.get(0));
        assertEquals(2, listCustomCapacity.get(0));
    }

    @Test
    void testAddInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.add(1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.add(-1, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.add(1, 4));
    }

    @Test
    void testGetSize() {
        assertEquals(0, listDefaultCapacity.getSize());
        assertEquals(0, listCustomCapacity.getSize());
        listDefaultCapacity.add(1);
        listCustomCapacity.add(1);
        assertEquals(1, listDefaultCapacity.getSize());
        assertEquals(1, listCustomCapacity.getSize());
    }

    @Test
    void testRemove() {

    }

    @Test
    void testStream() {
    }
}