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
        items = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) throws IllegalArgumentException {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer. Value passed: " + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.nextIndex = 0;
        items = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T item) {
        if (this.getSize() >= this.capacity) {
            this.capacity *= CAPACITY_INCREASE_MULTIPLIER;
            T[] newItems = (T[]) new Object[this.capacity];
            System.arraycopy(this.items, 0, newItems, 0, this.getSize());
            this.items = newItems;
        }
        this.items[this.nextIndex] = item;
        this.nextIndex++;
        return true;
    }

    @Override
    public boolean add(int index, T item) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.nextIndex) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }
    }

    @Override
    public int getSize() {
        return this.nextIndex;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.nextIndex) {
            throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds.");
        }
        return this.items[index];
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    // experimenting - made it streamable for learning purposes
    @Override
    public Stream<T> stream() {
        return Arrays.stream(items, 0, nextIndex);
    }

}
