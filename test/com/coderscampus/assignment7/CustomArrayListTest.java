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
    void testAddCapacityIncrease() {
        for (int i = 0; i < 100; i++) {
            listDefaultCapacity.add(i);
            listCustomCapacity.add(i);
        }
        assertEquals(160, listDefaultCapacity.getCapacity());
        assertEquals(160, listCustomCapacity.getCapacity());
    }

    @Test
    void testAddAtIndexCapacityIncrease() {
        for (int i = 0; i < 100; i++) {
            listDefaultCapacity.add(i, i);
            listCustomCapacity.add(i, i);
        }
        assertEquals(160, listDefaultCapacity.getCapacity());
        assertEquals(160, listCustomCapacity.getCapacity());
    }

    @Test
    void testCapacityIncreaseAtZero() {
        var test = new CustomArrayList<Integer>(0);
        test.add(1);
        assertEquals(1, test.getCapacity());
        test.remove(0);
        assertEquals(0, test.getCapacity());
    }

    @Test
    void testCapacityDecrease() {
        for (int i = 0; i < 100; i++) {
            listDefaultCapacity.add(i);
            listCustomCapacity.add(i);
        }
        System.out.println(listCustomCapacity);
        for (int i = 99; i > 0; i--) {
            listDefaultCapacity.remove(i);
            listCustomCapacity.remove(i);
        }
        System.out.println(listCustomCapacity);
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
    void testAdd() {
        listDefaultCapacity.add(1);
        listCustomCapacity.add(2);
        assertEquals(1, listDefaultCapacity.get(0));
        assertEquals(2, listCustomCapacity.get(0));
    }

    @Test
    void testAddAtIndex() {
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
    void testAddZeroCapacity() {
        listCustomCapacity = new CustomArrayList<>(0);
        listCustomCapacity.add(1);
        assertEquals(1, listCustomCapacity.get(0));
        assertEquals(1, listCustomCapacity.getCapacity());
        assertEquals(1, listCustomCapacity.getSize());
    }

    @Test
    void testAddAtIndexZeroCapacity() {
        listCustomCapacity = new CustomArrayList<>(0);
        listCustomCapacity.add(0, 1);
        assertEquals(1, listCustomCapacity.get(0));
        assertEquals(1, listCustomCapacity.getCapacity());
        assertEquals(1, listCustomCapacity.getSize());
    }

    @Test
    void testAddInvalidIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.add(1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.add(-1, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.add(1, 4));
    }

    @Test
    void testRemoveInvalidIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.remove(1));
    }

    @Test
    void testGetInvalidIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> listDefaultCapacity.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> listCustomCapacity.get(1));
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
        for (int i = 0; i < 10; i++) {
            listDefaultCapacity.add(i);
        }
        Integer removedMid = listDefaultCapacity.remove(4);
        assertEquals(4, removedMid);
        assertEquals(9, listDefaultCapacity.getSize());
        Integer removedBegin = listDefaultCapacity.remove(0);
        assertEquals(0, removedBegin);
        assertEquals(8, listDefaultCapacity.getSize());
        Integer removedEnd = listDefaultCapacity.remove(listDefaultCapacity.getSize() - 1);
        assertEquals(9, removedEnd);
        assertEquals(7, listDefaultCapacity.getSize());
    }

    @Test
    void testStream() {
        listDefaultCapacity.add(1);
        listCustomCapacity.add(1);
        assertEquals(1, listDefaultCapacity.stream().count());
        assertEquals(1, listCustomCapacity.stream().count());
    }
}