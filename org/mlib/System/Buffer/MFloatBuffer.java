package org.mlib.System.Buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

public class MFloatBuffer {
    public static FloatBuffer create(int size) {
        return ByteBuffer
                .allocateDirect(size)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
    }

    public static FloatBuffer create(Vector<Float> values) {
        FloatBuffer buffer = create(values.size() * 4);

        for (float value : values)
            buffer.put(value);

        return buffer;
    }

    public static FloatBuffer create(float[] data) {
        return FloatBuffer.wrap(data);
    }
}