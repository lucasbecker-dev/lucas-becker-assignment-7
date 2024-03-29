package com.coderscampus.assignment7;

import java.util.Arrays;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class CustomArrayList<T> implements CustomList<T> {
    private static final int CAPACITY_INCREASE_MULTIPLIER = 2;
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private int capacity;
    private int nextIndex;
    private T[] items;

    public CustomArrayList() {
        this.capacity = DEFAULT_INITIAL_CAPACITY;
        this.nextIndex = 0;
        this.items = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer. Value passed: " + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.nextIndex = 0;
        this.items = (T[]) new Object[initialCapacity];
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "capacity=" + capacity +
                ", nextIndex=" + nextIndex +
                ", size=" + getSize() +
                ", items=" + Arrays.toString(items) +
                '}';
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return nextIndex == 0;
    }

    @Override
    public boolean add(T item) {
        if (checkCapacityIncreaseNeeded()) {
            increaseCapacity();
        }
        items[nextIndex] = item;
        ++nextIndex;
        return true;
    }

    @Override
    public boolean add(int index, T item) {
        if (index < 0 || index > nextIndex) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }
        if (nextIndex + 1 >= capacity) {
            if (capacity == 0) {
                ++capacity;
            } else {
                capacity *= CAPACITY_INCREASE_MULTIPLIER;
            }
            T[] newItems = (T[]) new Object[capacity];
            System.arraycopy(items, 0, newItems, 0, index);
            newItems[index] = item;
            ++nextIndex;
            System.arraycopy(items, index, newItems, index + 1, nextIndex - index - 1);
            items = newItems;
        } else {
            System.arraycopy(items, index, items, index + 1, nextIndex - index);
            items[index] = item;
            ++nextIndex;
        }
        return true;
    }

    @Override
    public int getSize() {
        return nextIndex;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= nextIndex) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }
        return items[index];
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= nextIndex) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }
        int newCapacity;
        if (nextIndex - 1 == 0) {
            newCapacity = 0;
        } else if (nextIndex - 1 < capacity / CAPACITY_INCREASE_MULTIPLIER) {
            newCapacity = capacity / CAPACITY_INCREASE_MULTIPLIER;
        } else {
            newCapacity = capacity;
        }
        T toReturn = items[index];
        T[] newItems = (T[]) new Object[newCapacity];
        System.arraycopy(items, 0, newItems, 0, index);
        --nextIndex;
        System.arraycopy(items, index + 1, newItems, index, nextIndex - index);
        items = newItems;
        capacity = newCapacity;
        return toReturn;
    }

    @Override
    public Stream<T> stream() {
        return Arrays.stream(items, 0, nextIndex);
    }


    private void increaseCapacity() {
        if (capacity == 0) {
            ++capacity;
        } else {
            capacity *= CAPACITY_INCREASE_MULTIPLIER;
            T[] newItems = (T[]) new Object[capacity];
            System.arraycopy(items, 0, newItems, 0, nextIndex);
            items = newItems;
        }
    }

    private boolean checkCapacityIncreaseNeeded() {
        return (nextIndex >= capacity);
    }
}
