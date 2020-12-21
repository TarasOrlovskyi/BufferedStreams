package com.orlovskyi;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends OutputStream {
    private OutputStream outputStream;
    private static final int CAPACITY = 8 * 1024;
    private byte[] arrayForOutputByByte;
    int index;

    public BufferedOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        arrayForOutputByByte = new byte[CAPACITY];
    }

    public BufferedOutputStream(OutputStream outputStream, int capacity) {
        this.outputStream = outputStream;
        arrayForOutputByByte = new byte[capacity];
    }

    @Override
    public void write(int i) throws IOException {
        arrayForOutputByByte[index++] = (byte) i;

        if (index == arrayForOutputByByte.length) {
            outputStream.write(arrayForOutputByByte);
            index = 0;
        }
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        if (len < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        byte[] arrayForOutputByByteWithSetLength = new byte[len];
        for (int i = 0; i < arrayForOutputByByteWithSetLength.length; i++) {
            arrayForOutputByByteWithSetLength[i] = b[off];
            off++;
        }
        outputStream.write(arrayForOutputByByteWithSetLength);
    }

    @Override
    public void close() throws IOException {
        flush();
        outputStream.close();
    }

    @Override
    public void flush() throws IOException {
        if (index != arrayForOutputByByte.length && index > 0) {
            write(arrayForOutputByByte, 0, index);
        }
    }
}