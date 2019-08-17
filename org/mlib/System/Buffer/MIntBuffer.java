package org.mlib.System.Buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class MIntBuffer {
    public static IntBuffer create(int size) {
        return create(size, null);
    }

    public static IntBuffer create(int[] data) {
        return create(data.length * 4, data);
    }

    public static IntBuffer create(int size, int[] data) {
        return ByteBuffer
                .allocateDirect(size)
                .order(ByteOrder.nativeOrder())
                .asIntBuffer()
                .put(data);
    }
}