package com.dschepkin.javaCore.iterator.customList;

import java.util.Iterator;

public class CustomList<T> implements Iterable<T> {
    private int size;
    private T[] array;

    CustomList(int initialSize) {
        this.array = (T[]) new Object[initialSize + 1];
    }

    public void add(T value) {
        array[size++] = value;
    }

    public T getArrValue(int position) {
        return array[position];
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomListIterator();
    }

    private class CustomListIterator implements Iterator<T> {

        private int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            return array[currentIndex++];
        }
    }
}
