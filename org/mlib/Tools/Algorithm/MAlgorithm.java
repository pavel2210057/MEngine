package org.mlib.Tools.Algorithm;

import java.util.Vector;

public class MAlgorithm {
    public static <T extends Number> int max(T[] values) {
        return -1;
    }

    public static <T extends Number> int min(T[] values) {
        return -1;
    }

    public static <T> void swap(Vector<T> data, int indexFirst, int indexSecond) {
        T temp = data.get(indexFirst);
        data.set(indexFirst, data.get(indexSecond));
        data.set(indexSecond, temp);
    }
}