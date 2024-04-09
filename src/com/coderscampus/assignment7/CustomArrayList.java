package com.coderscampus.assignment7;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class CustomArrayList<T> implements CustomList<T> {
    private static final int CAPACITY_INCREASE_MULTIPLIER = 2;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private int capacity;
    private int size;
    private T[] items;

    public CustomArrayList() {
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.size = 0;
        this.items = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity must be >= 0. Value passed: " + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.size = 0;
        this.items = (T[]) new Object[initialCapacity];
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "capacity=" + capacity +
                ", nextIndex=" + size +
                ", size=" + getSize() +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T item) {
        return add(size, item);
    }

    @Override
    public boolean add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }

        increaseCapacityIfNeeded();

        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }

        items[index] = item;
        size++;

        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }
        return items[index];
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }

        T toReturn = items[index];

        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }

        items[size - 1] = null;
        size--;

        resizeItemsArray();

        return toReturn;
    }

    private void resizeItemsArray() {
        int newCapacity;
        if (size - 1 == 0) {
            newCapacity = 0;
        } else if (size - 1 < capacity / CAPACITY_INCREASE_MULTIPLIER) {
            newCapacity = capacity / CAPACITY_INCREASE_MULTIPLIER;
        } else {
            newCapacity = capacity;
        }

        if (capacity != newCapacity) {
            items = Arrays.copyOf(items, newCapacity);
            capacity = newCapacity;
        }
    }

    @Override
    public Stream<T> stream() {
        return Arrays.stream(items, 0, size);
    }


    private void increaseCapacityIfNeeded() {
        if (size >= capacity) {
            if (capacity == 0) {
                ++capacity;
            } else {
                capacity *= CAPACITY_INCREASE_MULTIPLIER;
            }
            items = Arrays.copyOf(items, capacity);
        }
    }
}
