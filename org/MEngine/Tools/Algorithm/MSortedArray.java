package org.MEngine.Tools.Algorithm;

import java.util.Vector;

public class MSortedArray<T extends Number> {
    private Vector<T> data;

    public MSortedArray() {}

    public MSortedArray(T[] data) {
        for (T value : data)
            push(value);
    }

    public void push(T item) {
        this.data.add(moreThen(item).size(), item);
    }

    public int indexOf(T item) {
        int
                low = 0,
                high = this.data.size() - 1,
                index = high / 2;

        while (low < high) {
            if (this.data.get(index).doubleValue() == item.doubleValue())
                return index;

            if (this.data.get(index).doubleValue() < item.doubleValue())
                high = index;
            else
                low = index;

            index = (low + high) / 2;
        }

        return -1;
    }

    public Vector<T> slice(int begin, int end) {
        return new Vector<>(this.data.subList(begin, end));
    }

    public Vector<T> moreThen(T item) {
        return slice(indexOf(item) + 1, this.data.size());
    }

    public Vector<T> lessThen(T item) {
        return slice(0, indexOf(item));
    }

    public Vector<T> getData() {
        return this.data;
    }
}